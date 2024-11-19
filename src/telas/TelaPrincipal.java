package telas;

import aplicacao.ACMEAirDrones;
import dados.CategoriaCarga;
import dados.Estado;
import dados.Transporte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
	private TelaPrincipal2 telaPrincipal2;

	private ACMEAirDrones app;
	public TelaPrincipal(ACMEAirDrones app) {
		super();
		 telaPrincipal2= new TelaPrincipal2(app);
		this.add(telaPrincipal2.getPainel());
		setTitle("Janela");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // torna visivel a tela

	}

}
