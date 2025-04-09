import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SingleLayerNeuralNetwork singleLayerNeuralNetwork = new SingleLayerNeuralNetwork(3, 26, 0.0, 0.05);
        PrepareDataset prepareDataset = new PrepareDataset();
        FileService fileService = new FileService();
        List<Entity> entities = fileService.loadData("src/dataText.csv");
        EntityResult entityResult = prepareDataset.trainTestSplit(entities);
        List<Entity> trainSet = entityResult.trainSet;
        List<Entity> testSet = entityResult.testSet;
        double[][] trainInputs = new double[trainSet.size()][26];
        int[] trainLabels = new int[trainSet.size()];
        for (int i = 0; i < trainSet.size(); i++) {
            trainInputs[i] = textToVector(trainSet.get(i).getText());
            trainLabels[i] = trainSet.get(i).getLabel();
        }
        singleLayerNeuralNetwork.trainLayer(trainInputs, trainLabels);

        List<Integer> realLabels = new ArrayList<>();
        List<Integer> predictedLabels = new ArrayList<>();
        for (Entity entity : testSet) {
            double[] features = textToVector(entity.getText());
            int real = entity.getLabel();
            int predicted = singleLayerNeuralNetwork.predict(features);
            realLabels.add(real);
            predictedLabels.add(predicted);
            System.out.println("Actual: " + entity.getLabel() + " | Predicted: " + predicted);
        }

        EvaluationMetrics evaluationMetrics = new EvaluationMetrics();
        evaluationMetrics.measureAccuracy(realLabels, predictedLabels);
        evaluationMetrics.measurePRF(realLabels, predictedLabels);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre your own text: ");
        String textToUserTest = scanner.nextLine();
        double[] userMatrix = textToVector(textToUserTest);
        int prediction = singleLayerNeuralNetwork.predict(userMatrix);
        if (prediction == 0) {
            System.out.println("This is french: " + prediction);
        } else if (prediction == 1) {
            System.out.println("This is englihs: " + prediction);
        } else if (prediction == 2) {
            System.out.println("This is deutsch: " + prediction);
        }

    }

    public static double[] textToVector(String text) {
        double[] freq = new double[26];
        text = text.toLowerCase().replaceAll("[^a-z]", "");
        for (char c : text.toCharArray()) {
            freq[c - 'a']++;
        }

        double length = text.length();
        for (int i = 0; i < 26; i++) {
            freq[i] /= length;
        }
        return freq;
    }

}