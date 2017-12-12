package com.nezcafe.calc; //путь пакета

import java.awt.EventQueue;	//импорт библиотек
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Color;

public class CreditCalcWithWB {

	private JFrame frame; //объявление переменных
	private JLabel amountLb;
	private JLabel procentLb;
	private JTextField procentText;
	private JTextField timeText;
	private JPanel actionPanel;
	private JLabel resultLb;
	private JLabel result2Lb;
	private JTextArea resultField;
	private JLabel errorLbl;
	private JToggleButton toggleButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					CreditCalcWithWB window = new CreditCalcWithWB(); //запуск класса CreditCalcWithWB с объявлением на него ссылки window
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreditCalcWithWB() {
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); //создание окна программы

		

		actionPanel = new JPanel(); //создание панели 

		actionPanel.setBounds(0, 0, 356, 172); //размер панели
		frame.getContentPane().add(actionPanel); // добавление панели в рамку
		actionPanel.setLayout(null); // layout (слой) отсутствует (Absolute)

		amountLb = new JLabel("Сумма кредита"); //Создание лейбла
		amountLb.setBounds(28, 8, 150, 20); //размеры лейбла
		actionPanel.add(amountLb); //добавление на панель

		JTextField amountText = new JTextField(); //текстовое поле

		amountText.setBounds(173, 8, 150, 20); //размеры пол¤
		actionPanel.add(amountText); //добавление на панель
		amountText.setColumns(10); 

		procentLb = new JLabel(
				"Процентная ставка");
		procentLb.setBounds(28, 39, 135, 20);
		actionPanel.add(procentLb);

		procentText = new JTextField();

		procentText.setBounds(173, 39, 150, 20);
		actionPanel.add(procentText);
		procentText.setColumns(10);

		JLabel timeLb = new JLabel(
				"Количество месяцев");
		timeLb.setBounds(28, 71, 135, 20);
		actionPanel.add(timeLb);

		timeText = new JTextField();

		timeText.setBounds(173, 71, 150, 20);
		actionPanel.add(timeText);
		timeText.setColumns(10);

		JButton resultButton = new JButton("Рассчитать"); //создание кнопки
		resultButton.setBounds(10, 141, 105, 20);
		actionPanel.add(resultButton);
		
		
		
		toggleButton = new JToggleButton("Список"); //кнопка для развертывани¤ приложени¤
		toggleButton.setEnabled(false); //не доступна для нажатия
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frame.getHeight() < 420) //
					frame.setSize(362, 420);
				else
					frame.setSize(362, 200);
			}
		});

		toggleButton.setBounds(241, 141, 105, 20);
		actionPanel.add(toggleButton);

		resultLb = new JLabel("Месячный кредит:"); 
		resultLb.setBounds(28, 102, 135, 20);
		actionPanel.add(resultLb);

		result2Lb = new JLabel("");
		result2Lb.setBounds(173, 102, 105, 20);
		actionPanel.add(result2Lb);
		
		errorLbl = new JLabel("Не все данные корректны или введены правильно!");
		errorLbl.setForeground(Color.RED); // цвет текста
		errorLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorLbl.setBounds(28, 121, 295, 14);
		errorLbl.setVisible(false);
		actionPanel.add(errorLbl);

		JPanel secondPanel = new JPanel(); 
		secondPanel.setBounds(10, 173, 336, 209);
		frame.getContentPane().add(secondPanel);
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));

		resultField = new JTextArea();
		resultField.setEditable(false);
		secondPanel.add(resultField);

		JScrollPane scrollPane = new JScrollPane(resultField); //панель со скроллингом
		secondPanel.add(scrollPane, BorderLayout.CENTER);

		resultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int amount = Integer.parseInt(amountText.getText());
					double procent = Double.parseDouble(procentText.getText());
					int time = Integer.parseInt(timeText.getText());

					resultField.setText("");
					ArrayList<Double> list = new ArrayList<Double>();

					Resulting result = new Resulting(amount, time, procent);
					result2Lb.setText("" + NumberFormat.getCurrencyInstance().format(result.getMontlyPayment()));
					
					toggleButton.setEnabled(true);
					errorLbl.setVisible(false);

					list = result.getMonthsArray();

					for (int i = 0; i < list.size(); i++) {
						resultField.setText(resultField.getText() + (i + 1) + " месяц: "
								+ NumberFormat.getCurrencyInstance().format(list.get(i)) + "\n");
					}
				} catch (Exception e) {
					errorLbl.setVisible(true);
				}

			}
		});
	}
}