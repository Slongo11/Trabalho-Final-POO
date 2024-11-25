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
	private JCheckBox salvarEmArquivoCheckBox;
	private ACMEAirDrones app;
	private TelaComandos tela;
	private boolean executado;
	public FormTelaComandos(TelaComandos tela,ACMEAirDrones app) {
		this.app = app;
		this.tela = tela;
		executado = false;
		executarButton.addActionListener(this);
		voltarButton.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			if (e.getSource() == executarButton) {
				String comando = textField1.getText();

				if (comando.equalsIgnoreCase("SIMULA")) {
					if(executado){
						JOptionPane.showMessageDialog(painel,
								"Comando ja executado",
								"Aviso",
								JOptionPane.WARNING_MESSAGE);
					}else{
						app.simulaCarrega();
						textArea1.setText("Comando executado.");
						textArea1.append("\n" + app.mostraAdicoes());
						executado = true;
					}
				}
				else if(salvarEmArquivoCheckBox.isSelected()){
					String local = textField1.getText();
					if(local.equalsIgnoreCase("SIMULA")){
						throw new Exception("Comando inválido ");
					}
					if(local.matches(".*\\..*")){
						throw new Exception("Arquivo com ponto não é permitido");
					}
					if(local.contains(" ")){
						throw new Exception("O arquivo não pode ter espaço em branco");
					}
					app.armazenaConteudo(local);
					textArea1.setText("Arquivo salvo com sucesso!");
				}else {
					String local = "arquivos/"+comando+".csv";
					if(app.carregaConteudo(local)){
						textArea1.setText("Comando executado.");
						textArea1.append("\n" + app.mostraAdicoes());
					}else{
						JOptionPane.showMessageDialog(painel,
								"Problema ao carregar arquivo",
								"Erro",
								JOptionPane.ERROR_MESSAGE);
					}

				}


			}else if(e.getSource() == voltarButton){
				tela.setVisible(false);
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(painel,
					ex.getMessage(),
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public JPanel getPainel() {
		return painel;
	}
}
