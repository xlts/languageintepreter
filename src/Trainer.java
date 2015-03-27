import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Trainer {

	private VectorSet[] trainingSets;
	final int dim;
	String lang;
	private Perceptron[] perceptrons;
	private String[] languages;
	private Map<String, VectorSet[]> langMap = new HashMap<String, VectorSet[]>();
	
	public Trainer(String lang, VectorSet[] trainingSets, String[] languages, int dim, Map<String, VectorSet[]> langMap){
		this.trainingSets = Arrays.copyOf(trainingSets, trainingSets.length);
		this.languages = Arrays.copyOf(languages, languages.length);
		this.lang = lang;
		this.dim = dim;
		this.langMap = langMap;
		
		perceptrons = new Perceptron[languages.length];
		
		for (int i = 0; i < languages.length; i++){
			perceptrons[i] = new Perceptron(languages[i], dim);
		}
	}
	
	public boolean train(double learningRate){
		double res, desiredOut;
		VectorSet[] currSetArr;
		int[] inputArr;
		boolean weightsChanged = false;
		
		for (Perceptron p : perceptrons){ //every perceptron is trained with a set of all languages this object was constructed with
			
			for (String lang : langMap.keySet()){
				currSetArr = langMap.get(lang);
				
				if (lang.equals(p.getLanguage()))
					desiredOut = 1;
				else 
					desiredOut = 0;
				
				for (VectorSet set : currSetArr){
					inputArr = set.getArr();
					res = p.compute(inputArr);
					if (res == desiredOut)
						continue;
					else {
						weightsChanged = true;
						p.learn((int) desiredOut, (int) res, inputArr, learningRate);
					}
				}
			}
		}
		
		return weightsChanged;
	}
	
	public void runTrainingAndTest(double learningRate, double acceptedErrorRate, VectorSet[] vSetArr){
		double errorRate = 100;
		int iterNo = 0;
		boolean weightsChanged = false;
		
		do {
			iterNo++;
			
			errorRate = runTest(vSetArr);
			
			if (errorRate < acceptedErrorRate)
				break;
			
			weightsChanged = train(learningRate);
			
		} while(weightsChanged);
		
		System.out.println("\nIterated " + iterNo + " times");
		evalUserFile();
	}
	
	public double runTest(VectorSet[] vSetArr){
		int iterNo = 0;
		int errorNo = 0;
		
		for (int i = 0; i < vSetArr.length; i++){
			 iterNo++;
			 if (vSetArr[i].getLang().equals(eval(vSetArr[i])))
					 continue;
			 else
				 errorNo++;
		}
		
		return (double) errorNo/iterNo;
	}
	
	public String eval(VectorSet vSet){
		double[] results = new double[perceptrons.length];
		for (int i = 0; i < perceptrons.length; i++){
			results[i] = perceptrons[i].computeRes(vSet.getArr());
		}
		
		System.out.println(Arrays.toString(results));
		System.out.println("Evaluated as " + perceptrons[getMaxIndex(results)].getLanguage());
		return perceptrons[getMaxIndex(results)].getLanguage();
	}
	
	public static int getMaxIndex(double[] arr){
		int maxIndex = -1;
		double max = -Double.MAX_VALUE;
		for (int i = 0; i < arr.length; i++){
			if (arr[i] > max){
				max = arr[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	
	public void evalUserFile(){
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		while (!input.equals("end")){
			
			System.out.println("\nType in your file's name. Must be in one of these languages:");
			for (Perceptron p : perceptrons)
				System.out.print(p.getLanguage() + " ");
			System.out.println("");
			
			input = scan.nextLine();
			
			VectorSet userFile = new VectorSet(input);
			eval(userFile);
		}
		scan.close();
	}
}
