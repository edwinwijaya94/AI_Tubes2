package knn;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import javafx.util.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KNN {

    private int kVar;

    private List<List<String>> dataSet;
    
    private List<List<String>> dataTest;

    private int attributeCount;

    private int datasetSize;

    private int datatestSize;
    
    private Set<String> setClass = new HashSet<String>();
    
    private HashMap<String, Integer> countTypeClass = new HashMap<>();
    
    private HashMap<String, Integer> temp = new HashMap<>(setClass.size());
    
    private HashMap<String, HashMap<String, Integer>> matriks = new HashMap<>();
    
    private ArrayList<String> predicted  = new ArrayList<>();
    
    private ArrayList<String> actual  = new ArrayList<>();

    public KNN(String[] args, int kVar) {
        String line = null;
        String tempStr[];
        
        try {
            this.kVar = kVar;
            FileReader fileReader = new FileReader(args[0]);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            dataSet = new ArrayList<>();
            line = bufferedReader.readLine();
            while(!line.equals("@data")){
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            while(line != null && !line.equals("%")) {
                tempStr = line.split(",");
                ArrayList<String> tempRes = new ArrayList<>();
                for(int i =0; i<tempStr.length;i++){
                    tempRes.add(tempStr[i]);
                }
                attributeCount = tempRes.size() - 1;
                dataSet.add(tempRes);
                line = bufferedReader.readLine();
            }
            
            
            if (bufferedReader != null) {
                try {
                    // Always close files.
                    bufferedReader.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
            datasetSize = dataSet.size();
            this.getTypeClass();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + args[0] + "'");                
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
        if(args[3].equals("Full Training") || args[3].equals("Use all available methods")){
            try {
                this.kVar = kVar;
                FileReader fileReader = new FileReader(args[1]);
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                dataTest = new ArrayList<>();
                line = bufferedReader.readLine();
                while(!line.equals("@data")){
                    line = bufferedReader.readLine();
                }
                line = bufferedReader.readLine();
                while(line != null && !line.equals("%")) {
                    tempStr = line.split(",");
                    ArrayList<String> tempRes = new ArrayList<>();
                    for(int i =0; i<tempStr.length;i++){
                        tempRes.add(tempStr[i]);
                    }
                    attributeCount = tempRes.size() - 1;
                    dataTest.add(tempRes);
                    line = bufferedReader.readLine();
                }

                if (bufferedReader != null) {
                    try {
                        // Always close files.
                        bufferedReader.close();
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                }
                datatestSize = dataTest.size();
                this.getTypeClass();
            }catch(FileNotFoundException ex) {
                System.out.println("Unable to open file '" + args[1] + "'");                
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }    
        }
    }
        /*try {
            this.kVar = kVar;
            // baca dataset
            CSVReader csvReader = new CSVReader(new FileReader(args[0]));
            
            dataSet = new ArrayList<>();
            String[] nextLine;
            // memasukan data ke data set
            while ((nextLine = csvReader.readNext()) != null) {
                List<String> lineList = new ArrayList<>();

                attributeCount = nextLine.length - 1;
                for (int i = 0; i < nextLine.length; i++) {
                    lineList.add(nextLine[i]);
                }
                dataSet.add(lineList);
            }
            datasetSize = dataSet.size();
            System.out.println("data set size : " + datasetSize);
            this.getTypeClass();
            
            
            //baca datatest
            if(args[3].equals("Full Training") || args[3].equals("Use all available methods")){
                CSVReader csvReader2 = new CSVReader(new FileReader(args[1]));
                dataTest = new ArrayList<>();
                // memasukan data ke data test
                while ((nextLine = csvReader2.readNext()) != null) {
                    List<String> lineList = new ArrayList<>();

                    attributeCount = nextLine.length - 1;
                    for (int i = 0; i < nextLine.length; i++) {
                        lineList.add(nextLine[i]);
                    }
                    dataTest.add(lineList);
                }
                datatestSize = dataTest.size();
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    // mengisi set dengan atribut class
    public void getTypeClass() {
        for(int i = 0; i < datasetSize; i++) {
            setClass.add(dataSet.get(i).get(attributeCount));
        }
        Iterator<String> it = setClass.iterator();
        while (it.hasNext()) {
            countTypeClass.put(it.next(), 0);
        }    
    }

    //megnisi matrik konvergen awal
    public void matriks() {
        String[] Class = setClass.toArray(new String[setClass.size()]);
        for (int i = 0; i < setClass.size(); i++) {
            HashMap<String, Integer> prediksi = new HashMap<String, Integer>();
            for(int j = 0; j < setClass.size(); j++) {
                prediksi.put(Class[j], 0);
            }
            matriks.put(Class[i], prediksi);
        }
    }
    
    public int getCorrectInstance(ArrayList<String> accurated, ArrayList<String> predicted) {
        int countCorrect = 0;
        for(int i=0; i<accurated.size();i++){
            if(accurated.get(i).equals(predicted.get(i))) {
                countCorrect++;
            }
        }
        return countCorrect;
    }
    
    public void getFullTrainingAccuracy() {
        int accurate = 0;
        this.matriks();
        for (int i = 0; i < datatestSize; i++) {
            List<Pair<Integer, List<String>>> difference = new ArrayList<>();
            for (int j = 0; j < datasetSize; j++) {                
                if (i != j) {
                    int diff = 0;

                    for (int k = 0; k < attributeCount; k++) {
                        if (!dataTest.get(i).get(k).equals(dataSet.get(j).get(k))) {
                            diff++;
                        }
                    }
                    difference.add(new Pair<>(diff, dataSet.get(j)));
                }
            }

            Collections.sort(difference, (p1, p2) -> p1.getKey() - p2.getKey());
            

            int positive = 0;
            for (int j = 0; j < kVar; j++) {
                if (difference.get(j).getValue().get(attributeCount).equals(dataTest.get(i).get(attributeCount))) {
                    positive++;
                }

                //mencari prediksi class
                Iterator<String> it = setClass.iterator();
                while (it.hasNext()) {
                    if (difference.get(j).getValue().get(attributeCount).equals(it.next())) {
                        String key = difference.get(j).getValue().get(attributeCount);
                        countTypeClass.put(key, countTypeClass.get(key)+1);
                    }
                }
            }
            
            if ((positive * setClass.size()) >= (kVar)) {
                accurate++;
            }
            
            //mencari maksimum
            Iterator<String> it2 = setClass.iterator();
            String keyPrediksi ="";
            int max = 0;
            while (it2.hasNext()) {
                String now = it2.next();
                if (countTypeClass.get(now) >= max) {
                    keyPrediksi = now;
                    max = countTypeClass.get(keyPrediksi);
                }
            }
            predicted.add(keyPrediksi);
            actual.add(dataTest.get(i).get(attributeCount));
            
            //mereset countTypeClass
            Iterator<String> itCount = setClass.iterator();
            while (itCount.hasNext()) {
                countTypeClass.put(itCount.next(), 0);
            }
            
            Integer value = matriks.get(actual.get(i)).get(predicted.get(i));
            matriks.get(actual.get(i)).put(predicted.get(i), value + 1);
        }
        
        Iterator it = setClass.iterator();
        for(int i = 97; i < setClass.size() + 97; i++) {
            System.out.printf("%c : %s\n",(char)i,it.next());
        }
        
        System.out.println();
        System.out.println("* = Correct Instance");
        System.out.println();
        
        System.out.print("      \t");
        
        for(int i = 97; i < setClass.size() + 97; i++) {
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
        System.out.println("Corretly Classified Instances : " + getCorrectInstance(actual, predicted) );
        System.out.println("Incorretly Classified Instances : " + (datatestSize - getCorrectInstance(actual, predicted)));
        System.out.println("Full Training Method:");
        System.out.printf("Accuration = %.2f %%\n\n",(float) accurate / (float) datasetSize *100);
    }
    
    
    public void getCrossValidationAccuracy( int segment) {
        int accurate = 0;
        this.matriks();
        predicted = new ArrayList<>();
        actual = new ArrayList<>();
        //final int segment = 10;
        int mod = datasetSize % segment;
        int start = 0;
        int end = -1;
        for (int i = 0; i < segment; i++) {
            start = end + 1;
            end = start + (datasetSize/segment) -1;
            if(i<mod)
                end++;

            for (int j = start; j <= end; j++) {
                List<Pair<Integer, List<String>>> difference = new ArrayList<>();
                for (int k = 0; k < datasetSize; k++) {
                    if ((k < start) || (k > end)) {
                        int diff = 0;

                        for (int l = 0; l < attributeCount; l++) {
                            if (!dataSet.get(j).get(l).equals(dataSet.get(k).get(l))) {
                                diff++;
                            }
                        }
                        difference.add(new Pair<>(diff, dataSet.get(k)));
                    }
                }

                Collections.sort(difference, (p1, p2) -> p1.getKey() - p2.getKey());

                int positive = 0;
                for (int k = 0; k < kVar; k++) {
                    if (difference.get(k).getValue().get(attributeCount).equals(dataSet.get(j).get(attributeCount))) {
                        positive++;
                    }
                    
                    //mencari prediksi class
                    Iterator<String> it = setClass.iterator();
                    while (it.hasNext()) {
                        if (difference.get(k).getValue().get(attributeCount).equals(it.next())) {
                            String key = difference.get(k).getValue().get(attributeCount);
                            countTypeClass.put(key, countTypeClass.get(key)+1);
                        }
                    }
                }

                if (positive*setClass.size() >= (kVar)) {
                    accurate++;
                }
                
                //mencari maksimum
                Iterator<String> it2 = setClass.iterator();
                String keyPrediksi ="";
                int max = 0;
                while (it2.hasNext()) {
                    String now = it2.next();
                    if (countTypeClass.get(now) >= max) {
                        keyPrediksi = now;
                        max = countTypeClass.get(keyPrediksi);
                    }
                }
                predicted.add(keyPrediksi);
                actual.add(dataSet.get(j).get(attributeCount));
                
                //mereset countTypeClass
                Iterator<String> itCount = setClass.iterator();
                while (itCount.hasNext()) {
                    countTypeClass.put(itCount.next(), 0);
                }
            
                Integer value = matriks.get(actual.get(j)).get(predicted.get(j));
                matriks.get(actual.get(j)).put(predicted.get(j), value + 1);
            }
        }
        
        Iterator it = setClass.iterator();
        for(int i = 97; i < setClass.size() + 97; i++) {
            System.out.printf("%c : %s\n",(char)i,it.next());
        }
        
        System.out.println();
        System.out.println("* = Correct Instance");
        System.out.println();
        
        System.out.print("      \t");
        
        for(int i = 97; i < setClass.size() + 97; i++) {
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
        System.out.println("Corretly Classified Instances : " + getCorrectInstance(actual, predicted) );
        System.out.println("Incorretly Classified Instances : " + (datasetSize - getCorrectInstance(actual, predicted)));
        System.out.println("10-Cross Validation Method:");
        System.out.printf("Accuration = %.2f %%\n\n",(float) accurate / (float) datasetSize *100);
    }
}
