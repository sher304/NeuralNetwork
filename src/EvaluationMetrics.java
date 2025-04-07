import java.util.List;

public class EvaluationMetrics {

    public double measureAccuracy(List<Integer> realLabels, List<Integer> predictedLabels){
        int correct = 0;
        for (int i = 0; i < realLabels.size(); i++) {
            if (realLabels.get(i).equals(predictedLabels.get(i))) {
                correct++;
            }
        }
        return (100.0 * correct) / realLabels.size();
    }

    public void measurePRF(List<Integer> realLabels, List<Integer> predictedLabels){
        int truePositive = 0;
        int trueNegative = 0;
        int falsePositive = 0;
        int falseNegative = 0;
        for (int i = 0; i < realLabels.size(); i++) {
            if (realLabels.get(i).equals(1) && predictedLabels.get(i).equals(1)) {
                truePositive++;
            } else if(realLabels.get(i).equals(0) && predictedLabels.get(i).equals(0)) {
                trueNegative++;
            } else if (realLabels.get(i).equals(0) && predictedLabels.get(i).equals(1)) {
                falsePositive++;
            } else if (realLabels.get(i).equals(1) && predictedLabels.get(i).equals(0)) {
                falseNegative++;
            }
        }

        double precision = truePositive + falsePositive == 0 ? 0 : (double)truePositive / (truePositive + falsePositive);
        double recall = truePositive + falseNegative == 0 ? 0 : (double)truePositive / (truePositive + falseNegative);
        double f1 = precision + recall == 0 ? 0 : 2 * (precision * recall) / (precision + recall);
        System.out.println("Precision: " + precision + "\nRecall: " + recall + "\nF1: " + f1);
    }
}
