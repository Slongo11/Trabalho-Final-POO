package telas;

import aplicacao.ACMEAirDrones;

import javax.swing.*;

public class TelaPrincipal extends JFrame {
	private FormTelaPrincipal telaPrincipal2;

	private ACMEAirDrones app;
	public TelaPrincipal(ACMEAirDrones app) {
		super();
		telaPrincipal2= new FormTelaPrincipal(app);
		this.add(telaPrincipal2.getPainel());
		setTitle("Janela Principal");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // torna visivel a tela

	}

}
