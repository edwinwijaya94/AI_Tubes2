/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author user
 */
public class NB {

    private DataSet dataSet;         // the data set
    private DataTest dataTest;         // the data test
    private static ArrayList<ArrayList<String>> frequentTable;
    private static ArrayList<ArrayList<String>> probabilityTable;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        if (args.length!=2)
        {
          System.out.println("Wrong usage. Type java KNN [data file] [data Set]");
        }
        else
        {
          // Read in argument
          DataSet dataSet = new DataSet(args[0]);
          DataTest dataTest = new DataTest(args[1]);
          
          ArrayList<Set<String>> value = new ArrayList<>();
          for(int i=0; i<36; i++){
              Set<String> temp = new HashSet<>();
              value.add(temp);
          }
          AttributeValue attributeValue = new AttributeValue(dataSet,value);
          //attributeValue.print(value);
          Set<String> classValue = new HashSet<>();
          classValue = dataSet.getClassValue();
//          dataSet.printClass(classValue);
          frequentTable = new ArrayList<>();
//          for(int i=0; i<100; i++){
//              ArrayList<String> temp = new ArrayList<>();
//              frequentTable.add(temp);
//          }
          probabilityTable = new ArrayList<>();
//          for(int i=0; i<23; i++){
//              ArrayList<String> temp = new ArrayList<>();
//              probabilityTable.add(temp);
//          }
          Algorithm algorithm = new Algorithm(dataSet, value, frequentTable, probabilityTable);
//          algorithm.printFrequent(frequentTable);
//          algorithm.printProbability(probabilityTable);
          ArrayList<String> finalClass = new ArrayList<>();
          finalClass = algorithm.getResult(dataSet, dataTest, probabilityTable);
          
//          for(int i=0;i<finalClass.size();i++){
//              System.out.println(finalClass.get(i));
//          }
          System.out.println("Accuration = " + algorithm.getFullTrainingAccuration(dataTest, finalClass) + "%");
        }
    }
}
