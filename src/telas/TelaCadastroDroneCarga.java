package telas;

import aplicacao.ACMEAirDrones;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TelaCadastroDroneCarga extends JDialog {
	private FormCadastroDroneCarga formCadastroCarga;
	public TelaCadastroDroneCarga(ACMEAirDrones app) {
		super();
		formCadastroCarga= new FormCadastroDroneCarga(this,app);
		this.add(formCadastroCarga.getPainel());
		setTitle("Janela");
		setSize(800, 600);
		this.setModal(true);
		setVisible(true); // torna visivel a tela
	}




}
