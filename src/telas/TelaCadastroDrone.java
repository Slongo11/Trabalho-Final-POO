package telas;

import aplicacao.ACMEAirDrones;

import javax.swing.*;

public class TelaCadastroDrone extends JDialog {
	private FormCadastroDrone formCadastroCarga;
	public TelaCadastroDrone(ACMEAirDrones app) {
		super();
		formCadastroCarga= new FormCadastroDrone(this,app);
		this.add(formCadastroCarga.getPainel());
		setTitle("Cadastro de Drones");
		setSize(800, 600);
		this.setModal(true);
		setVisible(true); // torna visivel a tela
	}




}
