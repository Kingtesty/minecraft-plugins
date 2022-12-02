package fr.kingtesty.forrun.randomchest;

import java.util.ArrayList;

public class Coffres {

	  private static ArrayList<String> Coffres;

	  public static void cleanCoffre() {
		  Coffres = new ArrayList<String>();
	   }

	   public static ArrayList<String> getCoffres() {
	      return Coffres;
	   }

	   public static void addCoffre(String xyz) {
		   Coffres.add(xyz);
	   }
	   public static void removeCoffre(String xyz) {
		   Coffres.remove(xyz);
	   }
	   public static String getLastChallenger() {
	      return Coffres.size() > 0?(String)Coffres.get(Coffres.size() - 1):null;
	   }
}
