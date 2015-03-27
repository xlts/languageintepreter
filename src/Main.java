import java.util.HashMap;
import java.util.Map;


public class Main {
	
	final static String GERMAN = "German";
	final static String SPANISH = "Spanish";
	final static String FINNISH = "Finnish";
	final static String FRENCH = "French";
	final static String AFRIKAANS = "Afrikaans";
	
	final static String[] langArr = {GERMAN, SPANISH, FINNISH, FRENCH, AFRIKAANS};

	public static void main(String[] args){
		
		Map<String, VectorSet[]> langMap = new HashMap<String, VectorSet[]>();
		
		VectorSet[] gerArr = {new VectorSet("files/de-1.txt", GERMAN), new VectorSet("files/de-2.txt", GERMAN), new VectorSet("files/de-3.txt", GERMAN), new VectorSet("files/de-4.txt", GERMAN), new VectorSet("files/de-5.txt", GERMAN)};
		VectorSet[] espArr = {new VectorSet("files/esp-1.txt", SPANISH), new VectorSet("files/esp-2.txt", SPANISH), new VectorSet("files/esp-3.txt", SPANISH), new VectorSet("files/esp-4.txt", SPANISH), new VectorSet("files/esp-5.txt", SPANISH)};
		VectorSet[] finArr = {new VectorSet("files/fi-1.txt", FINNISH), new VectorSet("files/fi-2.txt", FINNISH), new VectorSet("files/fi-3.txt", FINNISH), new VectorSet("files/fi-4.txt", FINNISH), new VectorSet("files/fi-5.txt", FINNISH)};
		VectorSet[] frArr = {new VectorSet("files/fr-1.txt", FRENCH), new VectorSet("files/fr-2.txt", FRENCH), new VectorSet("files/fr-3.txt", FRENCH), new VectorSet("files/fr-4.txt", FRENCH), new VectorSet("files/fr-5.txt", FRENCH)};
		VectorSet[] afrArr = {new VectorSet("files/af-1.txt",AFRIKAANS), new VectorSet("files/af-2.txt", AFRIKAANS), new VectorSet("files/af-3.txt", AFRIKAANS), new VectorSet("files/af-4.txt", AFRIKAANS), new VectorSet("files/af-5.txt", AFRIKAANS)};
		langMap.put("German", gerArr);
		langMap.put("Spanish", espArr);
		langMap.put("Finnish", finArr);
		langMap.put("French", frArr);
		langMap.put("Afrikaans", afrArr);
		
		VectorSet[] testArr = {new VectorSet("files/test/de.txt"), new VectorSet("files/test/esp.txt"), new VectorSet("files/test/fi.txt"), new VectorSet("files/test/fr.txt"), new VectorSet("files/eval/af.txt")};
		Trainer t1 = new Trainer(GERMAN, langMap.get(GERMAN), langArr, 26, langMap);
		t1.runTrainingAndTest(0.1, 0.1, testArr);
	}
}
