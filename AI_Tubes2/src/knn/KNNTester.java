package knn;

public class KNNTester {
    
    public KNNTester(){
        
    }
    
    public void Test(String[] argv) {
        KNN knn = new KNN(argv, Integer.parseInt(argv[4]));
        
        if(argv[3].equals("Full Training")){
            System.out.println("***Method : Full Training***");
            knn.getFullTrainingAccuracy();
        }
        else if(argv[3].equals("Cross Validation")){
            System.out.println("***Method : " + argv[5] + "-" + "Fold Cross Validation***");
            knn.getCrossValidationAccuracy(Integer.parseInt(argv[5]));
        }
        else if(argv[3].equals("Use all available methods")){
            System.out.println("***Method : Full Training***");
            knn.getFullTrainingAccuracy();
            System.out.println("***Method : " + argv[5] + "-" + "Fold Cross Validation***");
            knn.getCrossValidationAccuracy(Integer.parseInt(argv[5]));
        }
        else{ 
            System.out.println("KNN : Unrecognized method option !");
        }
        
    }

}
