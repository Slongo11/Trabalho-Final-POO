package telas;

import aplicacao.ACMEAirDrones;
import dados.CategoriaCarga;
import dados.Estado;
import dados.Transporte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame implements ActionListener {
	private JButton solicita;
	private JButton procura;
	private JButton cadastrarT;
	private JButton processaTransportes;
	private JButton limparArea;
	private JButton cadastrarD;
	//campos da aplicacao
	private JTextField campo1;
	private JComboBox<Estado> campo2;
	private JTextField campo3;
	private JTextField campo4;
	private JTextField campo5;
	private JTextField campo6;
	private JTextField campo7;

	private JTextArea resultado;

	private ACMEAirDrones app;
	public TelaPrincipal(ACMEAirDrones app) {
		super();
		this.app = app;
		setTitle("Cadastro novo Transporte");
		setSize(800,800);
		JPanel painelPrincipal = new JPanel(); // o principal painel
		GridLayout gridPrincipal = new GridLayout(1,1);
		painelPrincipal.setLayout(gridPrincipal);

		painelPrincipal.add(painelDaEsquerda());
		painelPrincipal.add(painelDaDireita());
		getContentPane().add(painelPrincipal); // adiciona a tela principal a ser mostrada
		setDefaultCloseOperation(EXIT_ON_CLOSE);// saida do X da interface

		solicita.addActionListener(this);
		procura.addActionListener(this);
		limparArea.addActionListener(this);
		cadastrarT.addActionListener(this);
		processaTransportes.addActionListener(this);
		setVisible(true); // torna visivel a tela

	}
	public JPanel painelDaEsquerda() {
		JPanel painelPrincipal = new JPanel();
		FlowLayout flowEsquerda = new FlowLayout(FlowLayout.RIGHT);
		GridLayout gridEsquerda = new GridLayout(2,1);
		painelPrincipal.setLayout(gridEsquerda);
		JPanel painelDados = new JPanel();

		painelDados.setLayout(gridEsquerda);
		painelDados.add(new JLabel("<html><h1>Pesquisa de Principal</h1></html>", JLabel.CENTER));
		JPanel painelCampo1 = new JPanel();
		painelCampo1.setLayout(flowEsquerda);

		campo1 = new JTextField(10);
		painelCampo1.add(new JLabel("Código do Transporte:"));
		painelCampo1.add(campo1);
		painelDados.add(painelCampo1);
		resultado = new JTextArea(40,30);
		JScrollPane painelAreaTexto = new JScrollPane(resultado);
		resultado.setEditable(false);

		painelPrincipal.add(painelDados);
		painelPrincipal.add(painelAreaTexto);

		return painelPrincipal;
	}

	public JPanel painelDaDireita() {
		JPanel painelPrincipal = new JPanel();
		GridLayout gridPrincipal = new GridLayout(6,1);
		painelPrincipal.setLayout(gridPrincipal);
		solicita = new JButton("Solicitar Atualização");
		procura = new JButton("Procurar");
		limparArea = new JButton("Limpar Area");
		cadastrarT = new JButton("Cadastrar Transporte");
		processaTransportes = new JButton("Processa Transporte Pendentes");
		cadastrarD = new JButton("Cadastrar Drones");
		Estado [] estado = {Estado.ALOCADO,Estado.TERMINADO,Estado.CANCELADO};
		campo2 = new JComboBox<Estado>(estado);
		painelPrincipal.add(campo2);
		painelPrincipal.add(procura);
		painelPrincipal.add(solicita);
		painelPrincipal.add(limparArea);
		painelPrincipal.add(processaTransportes);
		painelPrincipal.add(cadastrarT);
		painelPrincipal.add(cadastrarD);


		return painelPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == solicita) {
				int codigo = Integer.parseInt(campo1.getText());
				Transporte t = app.buscaTransporte(codigo);
				resultado.setText(app.alteraSituacaoTrasporte(t,(Estado) campo2.getSelectedItem()));

			}
			else if (e.getSource() == procura) {

				int codigo = Integer.parseInt(campo1.getText());
				Transporte t = app.buscaTransporte(codigo);
				resultado.setText(t.toString());

			}
			else if (e.getSource() == limparArea) {
				resultado.setText("");
			}
			else if (e.getSource() == cadastrarT) {
				new TelaCadastroTransporte(app);

			}
			else if (e.getSource() == processaTransportes) {
				app.processaTransportesPendentes();
			}
		}catch (Exception e1) {
			//TODO COLOCAR UMA MENSAGEM BONITA COMO UM POP UP
			resultado.setText(e1.getMessage());
		}
	}

	public void atulziaTransporte() {

	}
}
