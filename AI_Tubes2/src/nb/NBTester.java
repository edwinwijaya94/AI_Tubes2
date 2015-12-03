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

    private DataSet dataSet;           // the data set
    private DataTest dataTest;         // the data test
    private ArrayList<ArrayList<String>> frequentTable;
    private ArrayList<ArrayList<String>> probabilityTable;
    private ArrayList<ArrayList<String>> frequentTable2;
    private ArrayList<ArrayList<String>> probabilityTable2;
    
    public NBTester(){
        
    }
    
    public void NBFullTraining(String[] args, DataSet dataSet, DataTest dataTest){
        //Full Training
        ArrayList<Set<String>> value = new ArrayList<>();
        for(int i=0; i<dataSet.getDataSet().get(0).length; i++){
            Set<String> temp = new HashSet<>();
            value.add(temp);
        }
        AttributeValue attributeValue = new AttributeValue(dataSet,value);
        //attributeValue.print(value);
        Set<String> classValue = new HashSet<>();
        classValue = dataSet.getClassValue();

        frequentTable = new ArrayList<>();

        probabilityTable = new ArrayList<>();
        Algorithm algorithm = new Algorithm(dataSet, value, frequentTable, probabilityTable);

        ArrayList<String> finalClass = new ArrayList<>();
        finalClass = algorithm.getFullTrainingResult(dataSet, dataTest, probabilityTable);
        System.out.println("***Method : Full Training***");
        algorithm.printProbabilityClassValue();
        algorithm.printProbability(probabilityTable);
        System.out.println();
        algorithm.printFinalClass(finalClass);
        System.out.println();
        System.out.printf("Accuration = %.2f %%\n\n" ,algorithm.getFullTrainingAccuration(dataTest, finalClass));
        
        System.out.println();
//        Print Confusion Matrix
        System.out.println("Confusion Matrix:");
        algorithm.printConfusionMatrixFullTraining(dataSet, dataTest, finalClass);
    }
    
    public void NBCrossValidation(String[] args, DataSet dataSet){
        //Cross Validation
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
        crossFinalClass=algorithm2.getCrossValidationResult(dataSet, Integer.parseInt(args[5]));
        
        System.out.println("***Method : " + args[5] + "-" + "Fold Cross Validation***");
        System.out.println();
        algorithm2.printFinalClass(crossFinalClass);
        System.out.println();
        System.out.printf("Accuration = %.2f %%\n\n" ,algorithm2.getCrossValidationAccuration(dataSet, crossFinalClass));
    
        //        Print Confusion Matrix
        System.out.println();
        System.out.println("Confusion Matrix:");
        algorithm2.printConfusionMatrixCrossValidation(dataSet, crossFinalClass);
    }
    
    public void Test(String[] args) throws IOException {
        
        if (args.length != 6)
        {
          System.out.println("Missing args !");
        }
        else
        {
            
            if(args[3].equals("Full Training")){ // full training method
                // Read in argument
                DataSet dataSet = new DataSet(args[0]);
                DataTest dataTest = new DataTest(args[1]); 
                
                this.NBFullTraining(args, dataSet, dataTest);
            }
            else if(args[3].equals("Cross Validation")){
                // Read in argument
                DataSet dataSet = new DataSet(args[0]);
            
                this.NBCrossValidation(args, dataSet);
            }
            else if(args[3].equals("Use all available methods")){
                // Read in argument
                DataSet dataSet = new DataSet(args[0]);
                DataTest dataTest = new DataTest(args[1]); 
                
                this.NBFullTraining(args, dataSet, dataTest);
                this.NBCrossValidation(args, dataSet);
            }
            else{
                System.out.println("Naive Bayes : Unrecognized method option !");
            }
        }
    }
}
