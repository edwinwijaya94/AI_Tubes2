/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author user
 */
public class Algorithm {
    
    private ArrayList<Integer> sumClass;
    private HashMap<String, Double> probabilityClassValue;
    public Algorithm(DataSet dataSet, ArrayList<Set<String>> value, ArrayList<ArrayList<String>> frequentTable, ArrayList<ArrayList<String>> probabilityTable){
        probabilityClassValue = new HashMap<>();
        Table(dataSet, value, frequentTable, probabilityTable);
        //printProbability(probabilityTable);
        
    }
    
    //membuat tabel frequent dan probability
    public void Table(DataSet dataSet, ArrayList<Set<String>> value, ArrayList<ArrayList<String>> frequentTable, ArrayList<ArrayList<String>> probabilityTable){
        sumClass = new ArrayList<>();
       
        //menghitung jumlah tiap value pada class
        for(Iterator<String> it2 = dataSet.getClassValue().iterator(); it2.hasNext();){
            int countClass = 0;
            String nowClass = it2.next();
            
            for(int i=0;i<dataSet.getDataSet().size();i++){
                if (dataSet.getDataSet().get(i)[dataSet.getDataSet().get(i).length-1].equals(nowClass)){
                    countClass++;
                }
            }
            sumClass.add(countClass);
            probabilityClassValue.put(nowClass,(double)countClass/ (double)dataSet.getDataSet().size());
        }
        
          
//        Iterator<String> it4 = dataSet.getClassValue().iterator();
//        for(int i=0;i<sumClass.size();i++){
//            String nowClass = it4.next();
//            System.out.print("nowClass " + nowClass+" ");
//            System.out.println(sumClass.get(i));
//        }
//        
//        System.out.println("dataSet size=" + dataSet.getDataSet().size());
//        System.out.println("value size=" + value.size());
        //iterate for every attribute
        for(int i=0; i<dataSet.getDataSet().get(0).length;i++){
            int temp = 0;
            // iterate for every possible value in an attribute
            for(Iterator<String> it = value.get(i).iterator(); it.hasNext();){
                String now = it.next();
                int it3 = 0; // index of possible class
                temp++;
                //iterate for every possible class
                for(Iterator<String> it2 = value.get(dataSet.getDataSet().get(0).length-1).iterator(); it2.hasNext();){
                    String now2 = it2.next();
                    
                    // iterate over all instances
                    int countFrequent=0;
                    double countProbability=0.0;
                    for(int j=0;j<dataSet.getDataSet().size();j++){
//                            System.out.println(dataSet.getDataSet().get(j)[k]);
//                            System.out.println("itnext " + now);
                        if (dataSet.getDataSet().get(j)[i].equals(now) && dataSet.getDataSet().get(j)[dataSet.getDataSet().get(j).length-1].equals(now2)){
                            countFrequent++;
                            countProbability++;
                        }
                    }
                    //update freq table
                    ArrayList<String> resFreq = new ArrayList<>();
                    resFreq.add(now);
                    resFreq.add(now2);
                    resFreq.add(countFrequent+"");
                    frequentTable.add(resFreq);

                    // update prob table
                    countProbability = countProbability / sumClass.get(it3);
                    ArrayList<String> resProb = new ArrayList<>();
                    resProb.add(now);
                    resProb.add(now2);
                    resProb.add(countProbability+"");
                    probabilityTable.add(resProb);
                        
                    it3++;
                }
                
            }
        }
        
    }
    
    public void printFrequent(ArrayList<ArrayList<String>> frequentTable){
        System.out.println("freq table=");
        for(int k=0; k<frequentTable.size(); k++){
                for(int j=0; j<frequentTable.get(k).size(); j++)
                    System.out.print(frequentTable.get(k).get(j)+ " ");
                System.out.println();
            }
    }
    
    public void printProbability(ArrayList<ArrayList<String>> probabilityTable){
        System.out.println("Probability Table:");
        System.out.printf("%s\t|\t%s\t|\t%s\n","Attribute","Class","Probability");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        for(int k=0; k<probabilityTable.size(); k++){
            System.out.printf("%s\t|\t%s\t|\t%s\n",probabilityTable.get(k).get(0),probabilityTable.get(k).get(1),probabilityTable.get(k).get(2));

        }
    }
    
    public ArrayList<String> getFullTrainingResult(DataSet dataSet, DataTest dataTest, ArrayList<ArrayList<String>> probabilityTable){
        
        ArrayList<String> finalClass = new ArrayList<>();
        // count probability
        
        for(int i=0; i<dataTest.getDataTest().size();i++){
            ArrayList<ArrayList<String>> probabilityClass = new ArrayList<>();
            for(Iterator<String> it = dataSet.getClassValue().iterator(); it.hasNext();){
                String now = it.next();
                Double result = 1.0;
                for(int j=0;j<dataTest.getDataTest().get(i).length-1;j++){
                    int k=0;
                    //System.out.println("prob size=" + probabilityTable.size());
                    while(k<probabilityTable.size() && (!probabilityTable.get(k).get(0).equals(dataTest.getDataTest().get(i)[j]) || !probabilityTable.get(k).get(1).equals(now))){
                        k++;
                    }
                    if(k != probabilityTable.size())
                        result *= Double.parseDouble(probabilityTable.get(k).get(2));
                    else // probability value not found
                        result *=0;
                }
                result *= probabilityClassValue.get(now);
                ArrayList<String> hasil = new ArrayList<>();
                hasil.add(now);
                hasil.add(result.toString());
                probabilityClass.add(hasil);
            }
            double max = Double.parseDouble(probabilityClass.get(0).get(1));
            String kelas = probabilityClass.get(0).get(0);
            for(int l=1;l<probabilityClass.size();l++){
                double temp =Double.parseDouble(probabilityClass.get(l).get(1));
                if(temp > max){
                    max= temp;
                    kelas = probabilityClass.get(l).get(0);
                }
            }   
            finalClass.add(kelas);
        }
        return finalClass;
    }
    
    public ArrayList<String> getCrossValidationResult(DataSet dataSet, int segment){
        
        
        ArrayList<String> finalClass = new ArrayList<>();
        
        int totalSize = dataSet.getDataSet().size();
        //System.out.println("totalSize= " +totalSize + " segment= " + segment);
        int mod = totalSize % segment;
        int start;
        int end = -1;
        for (int i = 0; i < segment; i++) {
            
            DataSet modelSet =  new DataSet();
            DataTest testSet = new DataTest();
            
            start = end+1;
            end = start + (totalSize/segment) -1;
            if(i<mod)
                end++;
            //System.out.println("start= " + start + " end " + end);
            //separate modelSet and testSet from dataSet
            for (int j = 0; j < totalSize; j++) {
                if( j<start || j>end){ // insert to modelSet
                    modelSet.getDataSet().add(dataSet.getDataSet().get(j));
                }
                else{ // insert to testSet
                    testSet.getDataTest().add(dataSet.getDataSet().get(j));
                }
            }
            
//            System.out.println("prob table=");
//            for(int k=0; k<probabilityTable.size(); k++){
//                for(int j=0; j<probabilityTable.get(k).size(); j++)
//                    System.out.print(probabilityTable.get(k).get(j)+ " ");
//                System.out.println();
//            }
            ArrayList<ArrayList<String>> probabilityTable = new ArrayList<>();
            ArrayList<ArrayList<String>> frequentTable = new ArrayList<>();
            
            ArrayList<Set<String>> value = new ArrayList<>();
            for(int x=0; x<36; x++){
              Set<String> temp = new HashSet<>();
              value.add(temp);
            }
            AttributeValue attributeValue = new AttributeValue(modelSet,value);
            //dataSet.printDataSet();
            //Algorithm algorithmtemp = new Algorithm(dataSet, value, frequentTable, probabilityTable);
            //modelSet.printDataSet();
            Table(modelSet, value, frequentTable, probabilityTable);
            //printProbability(probabilityTable);
            
            finalClass.addAll(getFullTrainingResult(modelSet,testSet, probabilityTable));
            
        }
        return finalClass;
    }
    
    //menghasilkan akurasi pada algoritma naive bayes
    public double getFullTrainingAccuration(DataTest dataTest, ArrayList<String> finalClass){
        double accuration;
        double count = 0;
       
        for(int i=0;i<finalClass.size();i++){
            if (finalClass.get(i).equals(dataTest.getDataTest().get(i)[dataTest.getDataTest().get(i).length-1])){
                count++;
            }
        }
        double incorrect = finalClass.size()-count;
        //System.out.println();
        System.out.println("Correctly Classified Instances = " + count);
        System.out.println("Incorrectly Classified Instances = " + incorrect);
        accuration = count / finalClass.size() * 100;
        return accuration;
    }
    
    //
    public double getCrossValidationAccuration(DataSet dataSet, ArrayList<String> finalClass){
        double accuration;
        double count = 0;
       
        for(int i=0;i<finalClass.size();i++){
            if (finalClass.get(i).equals(dataSet.getDataSet().get(i)[dataSet.getDataSet().get(i).length-1])){
                count++;
            }
        }
        double incorrect = finalClass.size()-count;
        //System.out.println();
        System.out.println("Correctly Classified Instances = " + count);
        System.out.println("Incorrectly Classified Instances = " + incorrect);
        accuration = count / finalClass.size() * 100;
        return accuration;
    }
    
    // print all predicted class (finalClass) for dataset
    public void printFinalClass(ArrayList<String> finalClass){
        System.out.println("Predicted Class:");
        for(int i=0; i<finalClass.size(); i++){
            System.out.println("data-" + (i+1) + " " + finalClass.get(i)); //1-based index
        }
        System.out.println();
    }
    
    public void printProbabilityClassValue(){
        System.out.println("Probability Class Value:");
        System.out.printf("%s\t|\t%s\n","Class","Probability");
        System.out.println("------------------------------------------------------------");
        for(Iterator it2= probabilityClassValue.entrySet().iterator(); it2.hasNext();){
            Map.Entry pair = (Map.Entry)it2.next();
            System.out.printf("%s\t|\t%f\n", pair.getKey(),pair.getValue());
        }
        System.out.println();
    }
    
    // print confusion matrix full training
    public void printConfusionMatrixFullTraining(DataSet dataSet, DataTest dataTest, ArrayList<String> finalClass){
        HashMap<String, HashMap<String, Integer>> matriks = new HashMap<>();
        
        String[] Class = dataSet.getClassValue().toArray(new String[dataSet.getClassValue().size()]);
        for (int i = 0; i < dataSet.getClassValue().size(); i++) {
            HashMap<String, Integer> prediksi = new HashMap<String, Integer>();
            for(int j = 0; j < dataSet.getClassValue().size(); j++) {
                prediksi.put(Class[j], 0);
            }
            matriks.put(Class[i], prediksi);
        }
        for (int k=0;k<dataTest.getDataTest().size();k++){
            String actual = dataTest.getDataTest().get(k)[dataTest.getDataTest().get(k).length-1];
            finalClass.add(finalClass.get(k));
            Integer value = matriks.get(actual).get(finalClass.get(k));
            matriks.get(actual).put(finalClass.get(k), value + 1);
        }
        
        //print attribute directory
        Iterator it = dataSet.getClassValue().iterator();
        for(int i = 97; i < dataSet.getClassValue().size() + 97; i++) {
            System.out.printf("%c : %s\n",(char)i,it.next());
        }
        System.out.println();
        System.out.println("* = Correct Instance");
        System.out.println();
        
        System.out.print("      \t");
        //print isi matriks
        for(int i = 97; i < dataSet.getClassValue().size() + 97; i++) {
            System.out.printf("%c\t",(char)i);
        }
        
        System.out.println();
        
        //print kebawah
        int l = 97;
        for(String actual : matriks.keySet()) {
            System.out.print((char)l + "\t");
            l++;
            for(String predicted : matriks.get(actual).keySet()) {
                if (actual == predicted){
                    System.out.printf("*( %s )\t",matriks.get(actual).get(predicted));
                } else
                    System.out.printf("%s\t",matriks.get(actual).get(predicted));
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // print confusion matrix cross validation
    public void printConfusionMatrixCrossValidation(DataSet dataSet, ArrayList<String> finalClass){
        HashMap<String, HashMap<String, Integer>> matriks = new HashMap<>();
        
        String[] Class = dataSet.getClassValue().toArray(new String[dataSet.getClassValue().size()]);
        for (int i = 0; i < dataSet.getClassValue().size(); i++) {
            HashMap<String, Integer> prediksi = new HashMap<String, Integer>();
            for(int j = 0; j < dataSet.getClassValue().size(); j++) {
                prediksi.put(Class[j], 0);
            }
            matriks.put(Class[i], prediksi);
        }
        for (int k=0;k<dataSet.getDataSet().size();k++){
            String actual = dataSet.getDataSet().get(k)[dataSet.getDataSet().get(k).length-1];
            finalClass.add(finalClass.get(k));
            Integer value = matriks.get(actual).get(finalClass.get(k));
            matriks.get(actual).put(finalClass.get(k), value + 1);
        }
        
        //print attribute directory
        Iterator it = dataSet.getClassValue().iterator();
        for(int i = 97; i < dataSet.getClassValue().size() + 97; i++) {
            System.out.printf("%c : %s\n",(char)i,it.next());
        }
        System.out.println();
        System.out.println("* = Correct Instance");
        System.out.println();
        
        System.out.print("      \t");
        //print isi matriks
        for(int i = 97; i < dataSet.getClassValue().size() + 97; i++) {
            System.out.printf("%c\t",(char)i);
        }
        System.out.println();
        
        //print kebawah
        int l = 97;
        for(String actual : matriks.keySet()) {
            System.out.print((char)l + "\t");
            l++;
            for(String predicted : matriks.get(actual).keySet()) {
                if (actual == predicted){
                    System.out.printf("*( %s )\t",matriks.get(actual).get(predicted));
                } else
                    System.out.printf("%s\t",matriks.get(actual).get(predicted));
            }
            System.out.println();
        }
        System.out.println();
    }
}
