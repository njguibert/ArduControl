package Vistas;
import gnu.io.SerialPortEvent;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import Clases.Arduino;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.io.Console;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Interfaz {

	private JFrame frmArducontrolInterfaz;
	Arduino arduino;
	private JTextField textField;
	JLabel lblmonitorled13;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frmArducontrolInterfaz.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz() {
		initialize();
		arduino=new Arduino();
		arduino.setInterfaz(this);

	}

	public void actualizarmonitor(){
	
		  
		switch (arduino.dato){
		  case "LED":
			  if(arduino.valor.equalsIgnoreCase("1"))  lblmonitorled13.setText("Encendido");
			  else lblmonitorled13.setText("Apagado");
		  break;
		
		}

	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArducontrolInterfaz = new JFrame();
		frmArducontrolInterfaz.setAlwaysOnTop(true);
		frmArducontrolInterfaz.setTitle("ArduControl - Interfaz de Contol de Motores");
		frmArducontrolInterfaz.setBounds(100, 100, 563, 434);
		frmArducontrolInterfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArducontrolInterfaz.getContentPane().setLayout(null);
		
		frmArducontrolInterfaz.setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(14, 212, 523, 172);
		frmArducontrolInterfaz.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMonitor = new JLabel("Monitor de Estados");
		lblMonitor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMonitor.setBounds(10, 0, 178, 36);
		panel_1.add(lblMonitor);
		
		JLabel lblLedPin = new JLabel("LED PIN 13:");
		lblLedPin.setBounds(10, 47, 72, 14);
		panel_1.add(lblLedPin);
		
		JLabel lblmonitorled = new JLabel("Apagado");
		lblmonitorled.setBounds(83, 47, 105, 14);
		lblmonitorled13=lblmonitorled;
		panel_1.add(lblmonitorled);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(159, 11, 378, 130);
		frmArducontrolInterfaz.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCantPasos = new JLabel("Numero de Pasos");
		lblCantPasos.setBounds(10, 45, 123, 14);
		panel.add(lblCantPasos);
		
		JLabel lblMotorUnipolar = new JLabel("Motor Unipolar");
		lblMotorUnipolar.setBounds(10, 11, 123, 23);
		panel.add(lblMotorUnipolar);
		lblMotorUnipolar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		textField = new JTextField();
		textField.setBounds(143, 42, 56, 20);
		panel.add(textField);
		textField.setText("100");
		textField.setColumns(10);
		
		JButton btnArrancar = new JButton("Ejecutar!");
		btnArrancar.setBounds(92, 88, 118, 31);
		panel.add(btnArrancar);
		btnArrancar.setForeground(Color.WHITE);
		btnArrancar.setBackground(Color.GREEN);
		btnArrancar.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(14, 11, 137, 130);
		frmArducontrolInterfaz.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(10, 88, 105, 31);
		panel_2.add(btnApagar);
		btnApagar.setForeground(Color.WHITE);
		btnApagar.setBackground(Color.RED);
		btnApagar.setFont(new Font("Arial", Font.PLAIN, 19));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arduino.SendData("LED:0");
			}
		});
		
		JLabel lblPruebasLed = new JLabel("LED PIN 13");
		lblPruebasLed.setBounds(10, 11, 95, 23);
		panel_2.add(lblPruebasLed);
		lblPruebasLed.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JButton btnPrender = new JButton("Prender");
		btnPrender.setBounds(10, 40, 105, 37);
		panel_2.add(btnPrender);
		btnPrender.setForeground(Color.WHITE);
		btnPrender.setBackground(Color.GREEN);
		btnPrender.setFont(new Font("Arial", Font.PLAIN, 19));
		btnPrender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arduino.SendData("LED:1");
			}
		});
		btnArrancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arduino.SendData("MOTOR:" + textField.getText());
			}
		});

	}
}
