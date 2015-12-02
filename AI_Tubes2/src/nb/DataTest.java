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
import java.util.Set;

/**
 *
 * @author user
 */
public class DataTest {
    
    private ArrayList<String[]> dataTest;
    
    public DataTest(){
        dataTest = new ArrayList<>();
    }
    
    public DataTest(String filename) throws IOException {

        // This will reference one line at a time
        String line = null;
        String[] temp;

        try {
            //filename  = "src/" + filename;
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(filename);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            dataTest = new ArrayList<String[]>();
            line = bufferedReader.readLine();
            while(!line.equals("@data")){
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            while(line != null && !line.equals("%")) {
                temp = line.split(",");
                dataTest.add(temp);
                line = bufferedReader.readLine();
            }
            
//            for(int i=0;i<dataTest.size();i++){
//                for(int j=0;j<dataTest.get(i).length;j++){
//                    System.out.print(dataTest.get(i)[j] + " ");
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
    
    public ArrayList<String[]> getDataTest(){
        return dataTest;
    }
    
    public void printDataTest(){
        System.out.println("Data Test: size= " + dataTest.size());
        for(int i=0; i<dataTest.size(); i++){
            for(int j=0; j<dataTest.get(i).length;j++)
                System.out.print(dataTest.get(i)[j] + " ");
            System.out.println("");
        }
        
    }
}
