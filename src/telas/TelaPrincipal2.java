package telas;

import aplicacao.ACMEAirDrones;

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
	private ACMEAirDrones app;

	public TelaPrincipal2(ACMEAirDrones app) {
		this.app = app;



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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == limparButton) {
				textField1.setText("");
			} else if (e.getSource() == limparButton1) {
				principal.setText("");
			} else if (e.getSource() == pequisarButton) {
				//TODO FAZER A PEQUISA
				principal.setText("algo");
			} else if (e.getSource() == cadastrarNovoTransporteButton) {
				TelaCadastroTransporte trans = new TelaCadastroTransporte(app);
			} else if (e.getSource() == processarButton) {
				app.processaTransportesPendentes();
			}
			else if (e.getSource() == cadastrarNovoDronePessoalButton) {
				new TelaCadastroDrone(app);
			} else if (e.getSource() == cadastrarNovoDroneDeCargaButton) {
				//TODO TELA DE CADASTRO DE DRONE DE CARGA
			}else if (e.getSource() == finalizarButton) {
				System.exit(0);
			}
			else if (e.getSource() == relatorioButton) {
				//TODO RELATORIO
				principal.setText("Relatorio");
			}
		} catch (Exception e1) {
			//TODO COLOCAR UMA MENSAGEM BONITA COMO UM POP UP
			principal.setText(e1.getMessage());
		}
	}

		public JPanel getPainel() {
			return painel;
		}
}
