package calculator;

import java.util.HashMap;
import java.util.Map;

public class CIDIonCalculator {
	private Map<String, Double> massTable;
	private final static double bplus = 1.0078;
	private final static double yplus = 19.0184;
	
	public CIDIonCalculator() {
		massTable = new HashMap<String, Double>();
		massTable.put("A", 71.03711);
		massTable.put("R", 156.10111);
		massTable.put("N", 114.04293);
		massTable.put("D", 115.02694);
		massTable.put("C", 103.00919);
		massTable.put("E", 129.04259);
		massTable.put("Q", 128.05858);
		massTable.put("G", 57.02146);
		massTable.put("H", 137.05891);
		massTable.put("I", 113.08406);
		massTable.put("L", 113.08406);
		massTable.put("K", 128.09496);
		massTable.put("M", 131.04049);
		massTable.put("F", 147.06841);
		massTable.put("P", 97.05276);
		massTable.put("S", 87.03203);
		massTable.put("T", 101.04768);
		massTable.put("W", 186.07931);
		massTable.put("Y", 163.06333);
		massTable.put("V", 99.06841);
	}
	
	private String collision(String peptide, int pos) {
		String bIonStr = peptide.substring(0, pos);
		String yIonStr = peptide.substring(pos, peptide.length());
		
		double bIonMass = 0.0, yIonMass = 0.0;
		for (int i = 0; i < bIonStr.length(); i++) {
			bIonMass += massTable.get(bIonStr.charAt(i)+"");
		}
		for (int i = 0; i < yIonStr.length(); i++) {
			yIonMass += massTable.get(yIonStr.charAt(i)+"");
		}
		
		bIonMass += bplus;
		yIonMass += yplus;
		
		return String.format("%s %s;\t%.4f %.4f", bIonStr, yIonStr, bIonMass, yIonMass);
	}
	
	public void printIon(String peptide) {
		for (int i = 1; i < peptide.length(); i++) {
			String result = collision(peptide, i);
			System.out.println(result);
		}
	}
	
	public static void main(String[] args) {	
		CIDIonCalculator cal = new CIDIonCalculator();
		cal.printIon("AKADEDTWETAGSAK");
	}
}
