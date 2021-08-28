package calcCombustivel;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculaGastoCombustivel extends JFrame {

	private JPanel contentPane;
	private JTextField tfRazao;
	private JTextField tfLitros;
	private JTextField tfDistancia;
	private JTextField tfPreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculaGastoCombustivel frame = new CalculaGastoCombustivel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public CalculaGastoCombustivel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calcular Comb\u00FAstivel");
		setResizable(false);
		setSize(591,447);
		setLocationRelativeTo(null);
		contentPane =    new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 585, 418);
		contentPane.add(panel);
		panel.setLayout(null);
		

		
		tfLitros = new JTextField();
		tfLitros.setHorizontalAlignment(SwingConstants.CENTER);
		tfLitros.setEditable(false);
		tfLitros.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfLitros.setForeground(Color.BLACK);
		tfLitros.setBounds(453, 257, 81, 20);
		panel.add(tfLitros);
		tfLitros.setColumns(10);
		
		JLabel lblValor = new JLabel("");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Consolas", Font.PLAIN, 30));
		lblValor.setBounds(66, 103, 156, 60);
		panel.add(lblValor);
		
		JLabel lblRazao = new JLabel("Raz\u00E3o KM/L:");
		lblRazao.setFont(new Font("Consolas", Font.BOLD, 16));
		lblRazao.setBounds(392, 7, 104, 24);
		panel.add(lblRazao);
		
		tfRazao = new JTextField();
		tfRazao.setBounds(493, 8, 74, 20);
		panel.add(tfRazao);
		tfRazao.setColumns(10);
		
		JLabel lblDist = new JLabel("Dist\u00E2ncia:");
		lblDist.setFont(new Font("Consolas", Font.BOLD, 16));
		lblDist.setBounds(394, 61, 104, 24);
		panel.add(lblDist);
		
		JLabel lblComb = new JLabel("Comb\u00FAstivel:");
		lblComb.setHorizontalAlignment(SwingConstants.CENTER);
		lblComb.setFont(new Font("Consolas", Font.BOLD, 15));
		lblComb.setBounds(427, 235, 143, 24);
		panel.add(lblComb);
		
		tfDistancia = new JTextField();
		tfDistancia.setColumns(10);
		tfDistancia.setBounds(489, 62, 74, 20);
		panel.add(tfDistancia);
		


		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Consolas", Font.BOLD, 16));
		lblPreco.setBounds(398, 113, 104, 24);
		panel.add(lblPreco);
		
		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBounds(453, 114, 114, 20);
		panel.add(tfPreco);
		
		
		JButton btLimpar = new JButton("Limpar");
		btLimpar.setEnabled(false);
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfRazao.setText("");
				tfDistancia.setText("");
				tfPreco.setText("");
				tfLitros.setText("");
				lblValor.setText("");
				
				tfRazao.requestFocus();
				btLimpar.setEnabled(false);
			}
		});
		
		JButton btCalc = new JButton("Calcular");
		btCalc.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btCalc.setBounds(427, 175, 125, 31);
		panel.add(btCalc);
		btCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pattern letras = Pattern.compile("[A-Za-z]");
				
				Matcher procuraLetras = letras.matcher(tfRazao.getText().toString());
				boolean letrasRazao = procuraLetras.find();
				
				procuraLetras = letras.matcher(tfDistancia.getText().toString());
				boolean letrasDist = procuraLetras.find();
				
				procuraLetras = letras.matcher(tfPreco.getText().toString());
				boolean letrasPreco = procuraLetras.find();
				
				if(letrasRazao || letrasDist || letrasPreco) {
					
					lblValor.setText("Error");
					btLimpar.setEnabled(true);
					
				}
				
				else {
				
				if ((tfRazao.getText().toString()).equals("")
					||(tfDistancia.getText().toString()).equals("")
					||(tfPreco.getText().toString()).equals("")){
					lblValor.setText("Error");
					btLimpar.setEnabled(true);
				}
				
				else {
					double razao = Double.parseDouble(tfRazao.getText().toString().replace(",", "."));
					double distancia = Double.parseDouble(tfDistancia.getText().toString().replace(",", "."));
					double preco = Double.parseDouble(tfPreco.getText().toString().replace(",", "."));
					double litros = distancia/razao;
					tfLitros.setText(String.format("%.2f Litros", litros));
					lblValor.setText(String.format("R$ %.2f", (litros*preco)));
					
					btLimpar.setEnabled(true);
					}
				}
				
				
			}
		});
		
		btLimpar.setBounds(99, 292, 89, 23);
		panel.add(btLimpar);
		
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setIcon(new ImageIcon(CalculaGastoCombustivel.class.getResource("/img/bomba-de-gasolina.png")));
		imagemFundo.setBounds(-55, 0, 525, 512);
		panel.add(imagemFundo);
	}
}

