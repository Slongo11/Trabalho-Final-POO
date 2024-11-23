package telas;

import aplicacao.ACMEAirDrones;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCadastroDrone implements ActionListener {
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JButton cadastrarButton;
	private JButton limparButton;
	private JComboBox<CategoriaCarga> comboBox2;
	private JPanel painel;
	private JCheckBox protecaoCheckBox;
	private JCheckBox climatizadoCheckBox;
	private JTextArea resultado;
	private JButton voltarButton;
	private JButton limparButton1;
	private JButton mostraButton;
	private JTextField textField5;
	private TelaCadastroDrone telaCadastroDrone;
	private ACMEAirDrones app;
	public FormCadastroDrone(TelaCadastroDrone tela, ACMEAirDrones app) {
		this.app = app;
		telaCadastroDrone = tela;
		comboBox2.addItem(CategoriaCarga.PESSOAS);
		comboBox2.addItem(CategoriaCarga.CARGA_INANIMADA);
		comboBox2.addItem(CategoriaCarga.CARGA_VIVA);
		climatizadoCheckBox.setEnabled(false);
		protecaoCheckBox.setEnabled(false);
		textField4.setEnabled(false);
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
				if(CategoriaCarga.PESSOAS.equals(comboBox2.getSelectedItem())) {
					textField5.setEnabled(true);
					protecaoCheckBox.setEnabled(false);
					climatizadoCheckBox.setEnabled(false);
					textField4.setEnabled(false);
					textField4.setText("");
				}
				if(CategoriaCarga.CARGA_INANIMADA.equals(comboBox2.getSelectedItem())) {
					protecaoCheckBox.setEnabled(true);
					climatizadoCheckBox.setEnabled(false);
					textField5.setEnabled(false);
					textField4.setEnabled(true);
					textField5.setText("");

				}
				if(CategoriaCarga.CARGA_VIVA.equals(comboBox2.getSelectedItem())) {
					protecaoCheckBox.setEnabled(false);
					climatizadoCheckBox.setEnabled(true);
					textField5.setEnabled(false);
					textField4.setEnabled(true);
					textField5.setText("");
				}

			} else if (e.getSource() == limparButton) {
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");

			}
			else if (e.getSource() == limparButton1) {
				resultado.setText("");
			}else if (e.getSource() == cadastrarButton) {
				Drone d = null;
				int idCategoria = ((CategoriaCarga)comboBox2.getSelectedItem()).getCodigo();
				int codigo = Integer.parseInt(textField1.getText());
				if(codigo < 0){
					throw new Exception("Código não pose ser negativo");
				}
				double custoFixo = Double.parseDouble(textField2.getText());
				if(custoFixo <= 0){
					throw  new Exception("Custo fixo não pode ser negativo ou zero");
				}
				double autonomia = Double.parseDouble(textField3.getText());
				if(autonomia <= 0){
					throw new Exception("Autonomia não pode ser negativa ou zero");
				}
				boolean result;
				if(idCategoria == 1){
					int qtdPessoas = Integer.parseInt(textField5.getText());
					if(qtdPessoas <= 0){
						throw new Exception("Quantidade de pessoas não pode ser negativa ou zero");
					}
					d = new DronePessoal(codigo,custoFixo,autonomia,qtdPessoas);
				}

				if(idCategoria == 2) {
					double pesoMaximo = Double.parseDouble(textField4.getText());
					if(pesoMaximo <= 0){
						throw new Exception("Peso maximo não pode ser negativo ou zero");
					}
					result = protecaoCheckBox.isSelected();

					d = new DroneCargaInanimada(codigo,custoFixo,autonomia,pesoMaximo,result);
				}
				if(idCategoria == 3) {
					double pesoMaximo = Double.parseDouble(textField4.getText());
					if(pesoMaximo <= 0){
						throw new Exception("Peso maximo não pode ser negativo ou zero");
					}
					result = climatizadoCheckBox.isSelected();
					d = new DroneCargaViva(codigo,custoFixo,autonomia,pesoMaximo,result);
				}
				if(app.cadastrarDrone(d)){
					resultado.setText("Cadstrado com sucesso!");
				}else{
					resultado.setText("Não foi possível cadastrar código existente do drone");
				}
			}else if (e.getSource() == voltarButton) {
				telaCadastroDrone.setVisible(false);
			}else if (e.getSource() == mostraButton) {
				resultado.setText(app.mostraInfoDrone());
			}


			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(painel,
						"Campo digitado não e numérico!",
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
