package com.nezcafe.calc;

import java.util.ArrayList;


public class Resulting { //http://www.platesh.ru/differencirovannie-plateshi/ 
	

	private double osnPlatezh;
	private double montlyPayment = 0;
	private ArrayList<Double> monthsArray = new ArrayList(); 

	public double getOsnPlatezh() {
		return osnPlatezh;
	}

	public double getMontlyPayment() {
		return montlyPayment;
	}

	public ArrayList<Double> getMonthsArray() {
		return monthsArray;
	}

	public Resulting(int amount, int time, double procent) {
		int n = 0;
		osnPlatezh = amount / time; 

		while (n < time) {
			montlyPayment = (double)(osnPlatezh + (amount - (osnPlatezh * n)) * procent / 100 / 12);
			monthsArray.add(montlyPayment);
			n++;
			System.out.println(n + " мес€ц - " + montlyPayment);
		}
	}
}
