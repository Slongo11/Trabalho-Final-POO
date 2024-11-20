package telas;

import aplicacao.ACMEAirDrones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormTelaComandos implements ActionListener {
	private JButton executarButton;
	private JButton voltarButton;
	private JTextField textField1;
	private JTextArea textArea1;
	private JPanel painel;
	private ACMEAirDrones app;
	private TelaComandos tela;
	public FormTelaComandos(TelaComandos tela,ACMEAirDrones app) {
		this.app = app;
		this.tela = tela;
		executarButton.addActionListener(this);
		voltarButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == executarButton) {
			String comando = textField1.getText();
			if (comando.equalsIgnoreCase("SIMULA")) {
				app.armazenaConteudo();
			}
			if (comando.equalsIgnoreCase("FINAL")) {

			}

		}else if(e.getSource() == voltarButton){
			tela.setVisible(false);
		}
	}

	public JPanel getPainel() {
		return painel;
	}
}
