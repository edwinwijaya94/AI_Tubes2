/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author user
 */
public class Algorithm {
    
    public Algorithm(DataSet dataSet, ArrayList<Set<String>> value, ArrayList<ArrayList<String>> frequentTable, ArrayList<ArrayList<String>> probabilityTable){
        Table(dataSet, value, frequentTable, probabilityTable);
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
        
        for(int i=0; i<dataSet.getDataSet().get(i).length;i++){
            int temp = 0;
            for(Iterator<String> it = value.get(i).iterator(); it.hasNext();){
                String now = it.next();
                int it3 = 0;
                temp++;
                for(Iterator<String> it2 = value.get(dataSet.getDataSet().get(i).length-1).iterator(); it2.hasNext();){
                    String now2 = it2.next();
                    // iterate over all instances
                    int countFrequent=0;
                    double countProbability=0;
                    for(int j=0;j<dataSet.getDataSet().size();j++){
                        for(int k=0;k<dataSet.getDataSet().get(k).length;k++){
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
//                    System.out.println(countFrequent);
//                    System.out.println("now : " + now);
//                    System.out.println("now2 : " + now2);
                }
            }
        }
    }
    
    public void printFrequent(ArrayList<ArrayList<String>> frequentTable){
        for(int i=0;i<frequentTable.size();i++){
            for(int j=0;j<frequentTable.get(i).size();j++){
                System.out.print(frequentTable.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    
    public void printProbability(ArrayList<ArrayList<String>> probabilityTable){
        for(int i=0;i<probabilityTable.size();i++){
            for(int j=0;j<probabilityTable.get(i).size();j++){
                System.out.print(probabilityTable.get(i).get(j) + " ");
            }
        }
        System.out.print(probabilityTable.get(3).get(2) + " ");
    }
    
    public ArrayList<String> getResult(DataSet dataSet, DataTest dataTest, ArrayList<ArrayList<String>> probabilityTable){
        
        ArrayList<String> finalClass = new ArrayList<>();
        // count probability
        
        for(int i=0; i<dataTest.getDataTest().size();i++){
            ArrayList<ArrayList<String>> probabilityClass = new ArrayList<>();
            for(Iterator<String> it = dataSet.getClassValue().iterator(); it.hasNext();){
                String now = it.next();
                Double result = 1.0;
                for(int j=0;j<dataTest.getDataTest().get(i).length-1;j++){
                    int k=0;
                    while(!probabilityTable.get(k).get(0).equals(dataTest.getDataTest().get(i)[j]) || !probabilityTable.get(k).get(1).equals(now)){
                        k++;
                    }
                    result *= Double.parseDouble(probabilityTable.get(k).get(2));
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
    
    //menghasilkan akurasi pada algoritma naive bayes
    public double getFullTrainingAccuration(DataTest dataTest, ArrayList<String> finalClass){
        double accuration;
        double count = 0;
        //System.out.println("halo "+dataTest.getDataTest().get(1)[dataTest.getDataTest().get(1).length-1]);
        for(int i=0;i<finalClass.size();i++){
            
            if (finalClass.get(i).equals(dataTest.getDataTest().get(i)[dataTest.getDataTest().get(i).length-1])){
                count++;
                
            }
        }
        double incorrect = finalClass.size()-count;
        System.out.println();
        System.out.println("Correctly Classified Instances = " + count);
        System.out.println("Incorrectly Classified Instances = " + incorrect);
        accuration = count / finalClass.size() * 100;
        return accuration;
    }
    
}
