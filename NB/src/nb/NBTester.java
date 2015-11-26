/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author user
 */
public class NBTester {

    private DataSet dataSet;         // the data set
    private DataTest dataTest;         // the data test
    private ArrayList<ArrayList<String>> frequentTable;
    private ArrayList<ArrayList<String>> probabilityTable;
    private ArrayList<ArrayList<String>> frequentTable2;
    private ArrayList<ArrayList<String>> probabilityTable2;
    
    public NBTester(){
        
    }
    
    public void Test(String[] args) throws IOException {
        
        if (args.length != 2)
        {
          System.out.println("Wrong usage. Type java KNN [data file] [data Set]");
        }
        else
        {
          // Read in argument
          DataSet dataSet = new DataSet(args[0]);
          DataTest dataTest = new DataTest(args[1]);
          
          ArrayList<Set<String>> value = new ArrayList<>();
          for(int i=0; i<dataSet.getDataSet().get(0).length; i++){
              Set<String> temp = new HashSet<>();
              value.add(temp);
          }
          AttributeValue attributeValue = new AttributeValue(dataSet,value);
          //attributeValue.print(value);
          Set<String> classValue = new HashSet<>();
          classValue = dataSet.getClassValue();
//          dataSet.printClass(classValue);
          frequentTable = new ArrayList<>();

          probabilityTable = new ArrayList<>();
          Algorithm algorithm = new Algorithm(dataSet, value, frequentTable, probabilityTable);
//          algorithm.printProbability(probabilityTable);
//          algorithm.printFrequent(frequentTable);
          ArrayList<String> finalClass = new ArrayList<>();
          finalClass = algorithm.getFullTrainingResult(dataSet, dataTest, probabilityTable);
          
          System.out.println("Full Training method");
          System.out.printf("Accuration = %.2f %%\n\n" ,algorithm.getFullTrainingAccuration(dataTest, finalClass));
          
          //10 fold cross validation
          ArrayList<Set<String>> value2 = new ArrayList<>();
          for(int i=0; i<100; i++){
              Set<String> temp = new HashSet<>();
              value2.add(temp);
          }
          frequentTable2 = new ArrayList<>();
          probabilityTable2 = new ArrayList<>();
          //dataSet.printDataSet();
          Algorithm algorithm2 = new Algorithm(dataSet, value2, frequentTable2, probabilityTable2);
          //algorithm2.printProbability(probabilityTable);

          ArrayList<String> crossFinalClass = new ArrayList<>();
          crossFinalClass=algorithm2.getCrossValidationResult(dataSet, 10);

          System.out.println("10 Fold Cross Validation method:");
          System.out.printf("Accuration = %.2f %%\n\n" ,algorithm.getFullTrainingAccuration(dataTest, crossFinalClass));
          
        }
    }
}
