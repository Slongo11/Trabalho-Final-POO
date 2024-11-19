package telas;

import aplicacao.ACMEAirDrones;
import dados.Estado;
import dados.Transporte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal2 implements ActionListener {
	private JButton limparButton;
	private JButton finalizarButton;
	private JButton relatorioButton;
	private JButton cadastrarNovoDroneDeCargaButton;
	private JButton cadastrarNovoDronePessoalButton;
	private JButton cadastrarNovoTransporteButton;
	private JTextField textField1;
	private JButton pequisarButton;
	private JTextArea principal;
	private JButton limparButton1;
	private JPanel painel;
	private JButton processarButton;
	private JTextArea textArea1;
	private JScrollPane resultado;
	private JButton alterarStatusButton;
	private JComboBox<Estado> comboBox1;
	private ACMEAirDrones app;

	public TelaPrincipal2(ACMEAirDrones app) {
		this.app = app;
		comboBox1.addItem(Estado.ALOCADO);
		comboBox1.addItem(Estado.TERMINADO);
		comboBox1.addItem(Estado.CANCELADO);
		limparButton.addActionListener(this);
		limparButton1.addActionListener(this);
		finalizarButton.addActionListener(this);
		relatorioButton.addActionListener(this);
		cadastrarNovoDroneDeCargaButton.addActionListener(this);
		cadastrarNovoDronePessoalButton.addActionListener(this);
		cadastrarNovoTransporteButton.addActionListener(this);
		textField1.addActionListener(this);
		pequisarButton.addActionListener(this);
		processarButton.addActionListener(this);
		alterarStatusButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == limparButton) {
				textField1.setText("");
			} else if (e.getSource() == limparButton1) {
				textArea1.setText("");
			} else if (e.getSource() == pequisarButton) {
				String info = textField1.getText();
				int num = Integer.parseInt(info);
				Transporte t = app.buscaTransporte(num);
				if (t != null) {
					textArea1.setText(t.toString());
				}else {
					textArea1.setText("Erro ao buscar transporte");
				}

			} else if (e.getSource() == cadastrarNovoTransporteButton) {
				TelaCadastroTransporte trans = new TelaCadastroTransporte(app);
			} else if (e.getSource() == processarButton) {
				app.processaTransportesPendentes();
			}
			else if (e.getSource() == cadastrarNovoDronePessoalButton) {
				new TelaCadastroDrone(app);

			}
			else if (e.getSource() == cadastrarNovoDroneDeCargaButton) {
				TelaCadastroDroneCarga tela = new TelaCadastroDroneCarga(app);
			}
			else if (e.getSource() == finalizarButton) {
				System.exit(0);
			}
			else if (e.getSource() == relatorioButton) {

				textArea1.setText( "Transporte\n"+app.mostraInfoTransporte() +"\nDrones\n" + app.mostraInfoDrone());
			}
			else if (e.getSource() == alterarStatusButton) {
				String info = textField1.getText();
				int num = Integer.parseInt(info);
				Transporte t = app.buscaTransporte(num);
				Estado estado =(Estado) comboBox1.getSelectedItem();
				textArea1.setText(app.alteraSituacaoTrasporte(t,estado));
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(painel,
					"Codigo digitado nao e numerico!",
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
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
