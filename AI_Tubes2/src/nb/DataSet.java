/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author user
 */
public class DataSet {
    
    private ArrayList<String[]> dataSet;
    private Set<String> classValue;
    
    public DataSet(){
        dataSet = new ArrayList<>();
    }
    
    public DataSet(String filename) throws IOException {

        // This will reference one line at a time
        String line = null;
        String[] temp;

        try {
            // FileReader reads text files in the default encoding.
            
            FileReader fileReader = new FileReader(filename);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            dataSet = new ArrayList<String[]>();
            while((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                dataSet.add(temp);
            }
            
//            for(int i=0;i<dataSet.size();i++){
//                for(int j=0;j<dataSet.get(i).length;j++){
//                    System.out.print(dataSet.get(i)[j] + " ");
//                }
//                System.out.println();
//            }
            
            if (bufferedReader != null) {
                try {
                    // Always close files.
                    bufferedReader.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<String[]> getDataSet(){
        return dataSet;
    }
    
    public Set<String> getClassValue(){
        classValue = new HashSet<>();
        for(int i=0;i<dataSet.size();i++){
            classValue.add(dataSet.get(i)[dataSet.get(i).length-1]);
        }
        return classValue;
    }
    
    public void printClass(Set<String> classValue){
        for(Iterator<String> it = classValue.iterator(); it.hasNext();){
            System.out.print(it.next() + " ");
        }
    }
    
    public void printDataSet(){
        System.out.println("Data Set: size= " + dataSet.size());
        for(int i=0; i<dataSet.size(); i++){
            for(int j=0; j<dataSet.get(i).length;j++)
                System.out.print(dataSet.get(i)[j] + " ");
            System.out.println("");
        }
        
    }
}
