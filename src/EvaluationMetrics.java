import java.util.List;

public class EvaluationMetrics {

    public double measureAccuracy(List<Integer> realLabels, List<Integer> predictedLabels) {
        int correct = 0;
        for (int i = 0; i < realLabels.size(); i++) {
            if (realLabels.get(i).equals(predictedLabels.get(i))) {
                correct++;
            }
        }
        return (100.0 * correct) / realLabels.size();
    }
}
