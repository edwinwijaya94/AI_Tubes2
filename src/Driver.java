public class Driver {

    public static void main(String[] argv) {
        KNN knn = new KNN(argv[0], 4);

        System.out.println(knn.getFullTrainingAccuracy());
        System.out.println(knn.getCrossValidationAccuracy());
    }

}
