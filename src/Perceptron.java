
public class Perceptron {

	private double[] weights;
	String lang;
	final int dim;
	final static int threshold = 0;
	
	public Perceptron(String lang, int dim){
		this.lang = lang;
		this.dim = dim;
		weights = new double[dim+1];
	}
	
	public double compute(int[] input) {
		double res = 0;
		
		for (int i = 0; i < weights.length; i++){
			if (i == dim){
				res += weights[i]*-1;
			}
			else 
				res += weights[i]*input[i];
		}
		
		return res >= threshold ? 1 : 0;
	}
	
	public double computeRes(int[] input){
		double res = 0;
		
		for (int i = 0; i < weights.length; i++){
			if (i == dim){
				res += weights[i]*-1;
			}
			else 
				res += weights[i]*input[i];
		}
		
		return res;
	}
	
	
	public void learn(int desiredOut, int actualOut, int[] input, double learningRate){
		
		for (int i = 0; i < weights.length; i++){
			if (i == dim)
				weights[i] = weights[i] + (desiredOut - actualOut) * (-1) * learningRate;
			else 
				weights[i] = weights[i] + (desiredOut - actualOut) * input[i] * learningRate;
			
		}
	}
	
	public void printWeights(){
		System.out.print("weights: ");
		for (double w : weights)
			System.out.print(w + " ");
		System.out.println("");
	}
	
	public String getLanguage(){
		return this.lang;
	}
}
