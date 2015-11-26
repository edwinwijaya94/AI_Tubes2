package knn;

public class KNNTester {
    
    public KNNTester(){
        
    }
    
    public void Test(String[] argv, int distance) {
        KNN knn = new KNN(argv[0], distance);

        knn.getFullTrainingAccuracy();
        knn.getCrossValidationAccuracy();
    }

}
