/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author user
 */
public class Algorithm {
    
    public Algorithm(DataSet dataSet, ArrayList<Set<String>> value, ArrayList<ArrayList<String>> frequentTable, ArrayList<ArrayList<String>> probabilityTable){
        Table(dataSet, value, frequentTable, probabilityTable);
        //printProbability(probabilityTable);
    }
    
    //membuat tabel frequent dan probability
    public void Table(DataSet dataSet, ArrayList<Set<String>> value, ArrayList<ArrayList<String>> frequentTable, ArrayList<ArrayList<String>> probabilityTable){
        ArrayList<Integer> sumClass = new ArrayList<>();
       
        //menghitung jumlah tiap value pada class
        for(Iterator<String> it2 = dataSet.getClassValue().iterator(); it2.hasNext();){
            int countClass = 0;
            String nowClass = it2.next();
//            System.out.println("nowClass " + nowClass);
            for(int i=0;i<dataSet.getDataSet().size();i++){
                if (dataSet.getDataSet().get(i)[dataSet.getDataSet().get(i).length-1].equals(nowClass)){
                    countClass++;
                }
            }
            sumClass.add(countClass);
        }
//        for(int i=0;i<sumClass.size();i++){
//            System.out.println(sumClass.get(i));
//        }
//        System.out.println("dataSet size=" + dataSet.getDataSet().size());
//        System.out.println("value size=" + value.size());
        for(int i=0; i<dataSet.getDataSet().get(0).length;i++){
            int temp = 0;
            for(Iterator<String> it = value.get(i).iterator(); it.hasNext();){
                String now = it.next();
                int it3 = 0;
                temp++;
                for(Iterator<String> it2 = value.get(dataSet.getDataSet().get(0).length-1).iterator(); it2.hasNext();){
                    String now2 = it2.next();
                    // iterate over all instances
                    int countFrequent=0;
                    double countProbability=0;
                    for(int j=0;j<dataSet.getDataSet().size();j++){
                        for(int k=0;k<dataSet.getDataSet().get(0).length;k++){
//                            System.out.println(dataSet.getDataSet().get(j)[k]);
//                            System.out.println("itnext " + now);
                            if (dataSet.getDataSet().get(j)[k].equals(now) && dataSet.getDataSet().get(j)[dataSet.getDataSet().get(j).length-1].equals(now2)){
                                countFrequent++;
                                countProbability++;
                            }
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
        System.out.println("prob table=");
        for(int k=0; k<probabilityTable.size(); k++){
                for(int j=0; j<probabilityTable.get(k).size(); j++)
                    System.out.print(probabilityTable.get(k).get(j)+ " ");
                System.out.println();
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
    public double getCrossValidationAccuration(DataTest dataTest, ArrayList<String> finalClass){
        return getFullTrainingAccuration(dataTest,finalClass);
    }
}
