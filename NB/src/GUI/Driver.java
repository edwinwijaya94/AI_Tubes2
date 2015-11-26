/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.ArrayList;
import knn.*;
import nb.*;
/**
 *
 * @author Edwin
 */
public class Driver {
    public static void main(String[] argv) throws IOException{
        
        //NB Algo
        System.out.println("Naive Bayes Algorithm");
        NBTester NB = new NBTester();
        NB.Test(argv);
        
        //KNN Algo
        System.out.println("KNN Algorithm:");
        KNNTester KNN = new KNNTester();
        KNN.Test(argv,4);
        
    }
}
