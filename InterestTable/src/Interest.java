
import java.lang.Math;

public class Interest{

	public static double compound(double principle, int years, double rate) {
		double percent = rate/100;
		double compRate = Math.pow(1+percent, years);
		double comp = principle*compRate;
		return comp;
	}
	
	public static double simple(double principle, int years, double rate) {
		double percent = rate/100;
		double simpRate = principle*percent*years;
		double simp = principle + simpRate;
		return simp;
	}

}
