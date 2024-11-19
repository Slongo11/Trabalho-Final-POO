package telas;

import aplicacao.ACMEAirDrones;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCadastroDroneCarga implements ActionListener {
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JButton cadastrarButton;
	private JButton limparButton;
	private JComboBox<CategoriaCarga> comboBox2;
	private JPanel painel;
	private JCheckBox proteçãoCheckBox;
	private JCheckBox climatizadoCheckBox;
	private JTextArea resultado;
	private JButton voltarButton;
	private JButton limparButton1;
	private JButton mostraButton;
	private TelaCadastroDroneCarga telaCadastroDroneCarga;
	private ACMEAirDrones app;
	public FormCadastroDroneCarga(TelaCadastroDroneCarga tela,ACMEAirDrones app) {
		this.app = app;
		telaCadastroDroneCarga = tela;
		comboBox2.addItem(CategoriaCarga.CARGA_INANIMADA);
		comboBox2.addItem(CategoriaCarga.CARGA_VIVA);
		climatizadoCheckBox.setEnabled(false);
		limparButton.addActionListener(this);
		limparButton1.addActionListener(this);
		cadastrarButton.addActionListener(this);
		voltarButton.addActionListener(this);
		comboBox2.addActionListener(this);
		mostraButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == comboBox2) {
				if(CategoriaCarga.CARGA_INANIMADA.equals(comboBox2.getSelectedItem())) {
					proteçãoCheckBox.setEnabled(true);
					climatizadoCheckBox.setEnabled(false);
				}
				if(CategoriaCarga.CARGA_VIVA.equals(comboBox2.getSelectedItem())) {
					proteçãoCheckBox.setEnabled(false);
					climatizadoCheckBox.setEnabled(true);
				}

			} else if (e.getSource() == limparButton) {
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");

			}
			else if (e.getSource() == limparButton1) {
				resultado.setText("");
			}else if (e.getSource() == cadastrarButton) {
				DroneCarga d = null;
				int idCategoria = ((CategoriaCarga)comboBox2.getSelectedItem()).getCodigo();
				int codigo = Integer.parseInt(textField1.getText());
				double custoFixo = Double.parseDouble(textField2.getText());
				double altonomia = Double.parseDouble(textField3.getText());
				double pesoMaximo = Double.parseDouble(textField4.getText());
				boolean result = false;
				if(idCategoria == 2) {
					result = proteçãoCheckBox.isSelected();
					d = new DroneCargaInanimada(codigo,custoFixo,altonomia,pesoMaximo,result);
				}
				if(idCategoria == 3) {
					result = climatizadoCheckBox.isSelected();
					d = new DroneCargaViva(codigo,custoFixo,altonomia,pesoMaximo,result);
				}
				if(app.cadastrarDrone(d)){
					resultado.setText("Cadstrado com sucesso!");
				}else{
					resultado.setText("Não foi possível cadastrar código existente do drone");
				}
			}else if (e.getSource() == voltarButton) {
				telaCadastroDroneCarga.setVisible(false);
			}else if (e.getSource() == mostraButton) {
				resultado.setText(app.mostraInfoDroneCarga());
			}


			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(painel,
						"Codigo digitado nao e numerico!",
						"Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch(Exception e1){
				JOptionPane.showMessageDialog(painel,
						e1.getMessage(),
						"Erro",
						JOptionPane.ERROR_MESSAGE);
			}

	}
	public JPanel getPainel() {
		return painel;
	}
}
