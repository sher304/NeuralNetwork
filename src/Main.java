import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SingleLayerNeuralNetwork singleLayerNeuralNetwork = new SingleLayerNeuralNetwork(3, 26, 0.0, 0.05);

        List<String> texts = Arrays.asList(
                "Text in English.",
                "Text auf Deutsch",
                "Texte en allemand");
        int[] labels = {0,1,2};
        double[][] inputVectors = new double[texts.size()][26];

        for (int i = 0; i < texts.size(); i++) {
            inputVectors[i] = textToVector(texts.get(i));
        }

        singleLayerNeuralNetwork.trainLayer(inputVectors, labels);

        String newText = "This is a sample sentence.";
        double[] newVector = textToVector(newText);
        int predictedLang = singleLayerNeuralNetwork.predict(newVector);

        System.out.println("Predicted language index: " + predictedLang);
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