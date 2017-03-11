package calc;

import java.awt.Dimension; // подгружаем библиотеки
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreditCalculator{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CalcFrame frame = new CalcFrame();

			}
		});

	}

}

class CalcFrame extends JFrame {
	CalcFrame() {
		setSize(600, 200);
		setTitle(" редитный калькул€тор");
		CalcPanel panel = new CalcPanel();
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		pack();
	}
}

class CalcPanel extends JPanel {
	public CalcPanel() {
		JTextField procentTxt = new JTextField();
		JTextField timeTxt = new JTextField();
		JTextField summaTxt = new JTextField();
		JLabel platezhLabel = new JLabel("");

		procentTxt.setPreferredSize(new Dimension(20, 20));
		JButton b1 = new JButton();
		b1.setText("–ассчитать");

		try{
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int summa = Integer.parseInt(summaTxt.getText()); //первоначальна€ сумма кредита
				double procent = Double.parseDouble(procentTxt.getText()); //процентна€ ставка
				int time = Integer.parseInt(timeTxt.getText()); //в мес€цах!!!

				String result = String.valueOf(NumberFormat.getCurrencyInstance().format(summa/time));
				platezhLabel.setText(result); //мес€чный платеж
				calculateMonthlyPayment(summa, time, procent);
				
				
			}

		});
		}
		catch(Exception IO)
		{
		}
		setLayout(new GridLayout(6, 0, 3, 8));
		add(new JLabel("—умма кредита: "));
		add(summaTxt);
		add(new JLabel("ѕроцентна€ ставка (%): "));
		add(procentTxt);
		add(new JLabel(" оличество мес€цев: "));
		add(timeTxt);


		add(new JLabel(" "));
		add(b1);
		add(new JLabel("ћес€чный платеж: "));
		add(platezhLabel);

	}



public static void calculateMonthlyPayment(int summa, int time, double procent) {       
		ArrayList <Double> monthsArray = new ArrayList <Double>(); // http://www.platesh.ru/differencirovannie-plateshi/ дифференцированные платежи
	
		int osnPlatezh = summa/time; //сумма основного платежа без процентов
		int n =0;
		double montlyPayment = 0;
		
		while (n<time){
	    montlyPayment = osnPlatezh+(summa-(osnPlatezh*n))*procent/100/12;//n-остаток мес€цев
	    monthsArray.add(montlyPayment);
	    n++;
	    System.out.println(n+ " мес€ц - "+montlyPayment);
		}
	     
	   }

} 

