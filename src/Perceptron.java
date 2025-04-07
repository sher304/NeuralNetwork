import java.util.Random;

public class Perceptron {
    private int dimension;
    private double[] weights;
    private double threshold;
    private double learningRate;

    public Perceptron(int dimension, double threshold, double learning_rate) {
        this.dimension = dimension;
        this.weights = new double[dimension];
        this.threshold = threshold;
        this.learningRate = learning_rate;

        Random rand = new Random();
        for (int i = 0; i < dimension; i++) {
            weights[i] = -1 + 2 * rand.nextDouble();
        }
    }

    public int predict(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sum - threshold >= 0 ? 1:0;
    }

    public void train(double[][] inputs, int[] desiredOutput) {
        int epoch = 0;
        int dataSize = inputs.length;

        while (epoch < 10000) {
            int totalError = 0;
            int correct = 0;

            for (int i = 0; i < inputs.length; i++) {
                int actualOutput = predict(inputs[i]);
                int error = desiredOutput[i] - actualOutput;
                if (error != 0) {
                    totalError += Math.abs(error);
                    for (int j = 0; j < inputs[i].length; j++) {
                        weights[j] += learningRate * error * inputs[i][j];
                    }
                    threshold -= learningRate * error;
                } else {
                    correct++;
                }
            }
            double accuracy = (100.0 * correct) / dataSize;
            System.out.println("Epoch: " + epoch + " "  + "Accuracy: " + accuracy);
            if (totalError == 0) break;
            epoch++;
        }
    }

    public double[] getWeights() {
        return weights;
    }

    public double net(double[] input) {
        double sum = 0.0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i] * weights[i];
        }
        return sum - threshold;
    }

}
