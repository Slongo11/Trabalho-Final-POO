package telas;

import aplicacao.ACMEAirDrones;

import javax.swing.*;

public class TelaComandos extends JDialog {
	private FormTelaComandos comando;
	public TelaComandos(ACMEAirDrones app) {
		super();
		FormTelaComandos comando= new FormTelaComandos(this,app);
		this.add(comando.getPainel());
		setTitle("Comandos");
		setSize(800, 500);
		this.setModal(true);
		setVisible(true); // torna visivel a tela
	}
}
