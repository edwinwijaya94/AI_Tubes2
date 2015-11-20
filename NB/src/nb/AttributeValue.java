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
public class AttributeValue {
        
    //Value pada seluruh attribute
    public AttributeValue(DataSet dataSet, ArrayList<Set<String>> value){
        for(int i=0;i<dataSet.getDataSet().get(i).length;i++){
            for (int j=0; j<dataSet.getDataSet().size();j++){
                value.get(i).add(dataSet.getDataSet().get(j)[i]);
            }
        }
    }
        
    public void print(ArrayList<Set<String>> value){
        for(int i=0; i<value.size();i++){
            System.out.print("Attribute" + (i+"") + " ");
            for(Iterator<String> it = value.get(i).iterator(); it.hasNext();){
                 System.out.print(it.next() + " ");
            }
            System.out.println();
        }
    }
}
