import com.opencsv.CSVReader;
import javafx.util.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KNN {

    private int kVar;

    private List<List<String>> data;

    private int attributeCount;

    private int datasetSize;

    public KNN(String fileName, int kVar) {
        try {
            this.kVar = kVar;

            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            data = new ArrayList<>();

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                List<String> lineList = new ArrayList<>();

                attributeCount = nextLine.length - 1;
                for (int i = 0; i < nextLine.length; i++) {
                    lineList.add(nextLine[i]);
                }
                data.add(lineList);
            }

            datasetSize = data.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getFullTrainingAccuracy() {
        int accurate = 0;

        for (int i = 0; i < datasetSize; i++) {
            List<Pair<Integer, List<String>>> difference = new ArrayList<>();

            for (int j = 0; j < datasetSize; j++) {
                if (i != j) {
                    int diff = 0;

                    for (int k = 0; k < attributeCount; k++) {
                        if (!data.get(i).get(k).equals(data.get(j).get(k))) {
                            diff++;
                        }
                    }
                    difference.add(new Pair<>(diff, data.get(j)));
                }
            }

            Collections.sort(difference, (p1, p2) -> p1.getKey() - p2.getKey());

            int positive = 0;
            for (int j = 0; j < kVar; j++) {
                if (difference.get(j).getValue().get(attributeCount).equals(data.get(i).get(attributeCount))) {
                    positive++;
                }
            }

            if (positive >= (kVar - positive)) {
                accurate++;
            }
        }

        return (float) accurate / (float) datasetSize;
    }

    public float getCrossValidationAccuracy() {
        int accurate = 0;
        final int segment = 10;

        for (int i = 0; i < segment; i++) {
            int start = datasetSize * i / segment;
            int end = datasetSize * (i + 1) / segment;

            for (int j = start; j < end; j++) {
                List<Pair<Integer, List<String>>> difference = new ArrayList<>();
                for (int k = 0; k < datasetSize; k++) {
                    if ((k < start) || (k >= end)) {
                        int diff = 0;

                        for (int l = 0; l < attributeCount; l++) {
                            if (!data.get(j).get(l).equals(data.get(k).get(l))) {
                                diff++;
                            }
                        }
                        difference.add(new Pair<>(diff, data.get(k)));
                    }
                }

                Collections.sort(difference, (p1, p2) -> p1.getKey() - p2.getKey());

                int positive = 0;
                for (int k = 0; k < kVar; k++) {
                    if (difference.get(k).getValue().get(attributeCount).equals(data.get(j).get(attributeCount))) {
                        positive++;
                    }
                }

                if (positive >= (kVar - positive)) {
                    accurate++;
                }
            }
        }

        return (float) accurate / (float) datasetSize;
    }
}
