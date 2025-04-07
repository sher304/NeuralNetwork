import java.util.ArrayList;
import java.util.List;

public class SingleLayerNeuralNetwork {

    private List<Perceptron> neurons;
    private double threshold;
    private double learningRate;

    public SingleLayerNeuralNetwork(int dimension, int inputDimension, double threshold, double learningRate) {
        this.neurons = new ArrayList<>();
        this.learningRate = learningRate;
        this.threshold = threshold;

        for (int i = 0; i < dimension; i++) {
            neurons.add(new Perceptron(inputDimension, threshold, learningRate));
        }
    }

    public void trainLayer(double[][] inputs, int[] labels) {
        for (int i = 0; i < neurons.size(); i++) {
            int[] perceptrLabels = new int[labels.length];
            for (int j = 0; j < labels.length; j++) {
                perceptrLabels[j] = (labels[j] == i) ? 1 : 0;
            }
            neurons.get(i).train(inputs, perceptrLabels);
        }
    }

    public int predict(double[] input) {
        int chosenLanguageIndex = -1;
        double highestNetValue = -9999.0;
        for (int i = 0; i < neurons.size(); i++) {
            double net = neurons.get(i).net(input);
            if (net >= 0 && net > highestNetValue) {
                highestNetValue = net;
                chosenLanguageIndex = i;
            }
        }
        return chosenLanguageIndex;
    }

}
