package telas;
import aplicacao.ACMEAirDrones;
import dados.CategoriaCarga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCadastroTransporte extends JFrame implements ActionListener {
	/*
	Cadastrar novo transporte (solicita e cadastra os dados de um pedido de
	transporte. [Se o número do transporte já existir, não o cadastra e mostra uma
	mensagem de erro]. O novo pedido de transporte é colocado em uma fila de
	transportes pendentes).
	 */
	//botoes da aplicacao
	private JButton salvar;
	private JButton limpar;
	private JButton mostrar;
	private JButton fim;
	private JButton limparArea;
	//campos da aplicacao
	private JComboBox campo1;
	private JTextField campo2;
	private JTextField campo3;
	private JTextField campo4;
	private JTextField campo5;
	private JTextField campo6;
	private JTextField campo7;
	private JTextField campo8;
	private JTextField campo9;
	private JTextField campo10;
	private JTextField campo11;
	private JTextField campo12;
	private JComboBox campo13;
	//resultado para o usuário
	private JTextArea resultado;
	private JPanel painelEsquerda;
	private JPanel painelCampo10;
	private JPanel painelCampo11;
	private JPanel painelCampo12;
	private JPanel botoes1;

	private ACMEAirDrones app;

	public TelaCadastroTransporte(ACMEAirDrones app) {
		super();
		this.app = app;
		setTitle("Cadastro novo Transporte");
		setSize(800,800);
		JPanel painelPrincipal = new JPanel(); // o principal painel
		GridLayout gridPrincipal = new GridLayout(1,1);
		painelPrincipal.setLayout(gridPrincipal);


		painelPrincipal.add(geraPainelEsquerda());
		painelPrincipal.add(geraPainelDireita());
		getContentPane().add(painelPrincipal); // adiciona a tela principal a ser mostrada
		setDefaultCloseOperation(EXIT_ON_CLOSE);// saida do X da interface

		//botoes a serem esperados alguma acao
		salvar.addActionListener(this);
		limpar.addActionListener(this);
		mostrar.addActionListener(this);
		fim.addActionListener(this);
		limparArea.addActionListener(this);
		campo1.addActionListener(this);

		setVisible(true); // torna visivel a tela
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == salvar) {
			ArrayList<String> info = new ArrayList<>();
			info.add(""+((String)campo1.getSelectedItem()).charAt(0));
			info.add(campo2.getText());
			info.add(campo3.getText());
			info.add(campo4.getText());
			info.add(campo5.getText());
			info.add(campo6.getText());
			info.add(campo7.getText());
			info.add(campo8.getText());
			info.add(campo9.getText());
			info.add(campo10.getText());
			info.add(campo11.getText());
			info.add(campo12.getText());
			info.add((String)campo13.getSelectedItem());
			String informacao = app.leInfoTransporte(info);
			resultado.setText(informacao);
		}
		else if (e.getSource() == limpar) {
			campo1.setSelectedIndex(0);
			campo2.setText("");
			campo3.setText("");
			campo4.setText("");
			campo5.setText("");
			campo6.setText("");
			campo7.setText("");
			campo8.setText("");
			campo9.setText("");
			campo10.setText("");
			campo11.setText("");
			campo12.setText("");
			campo13.setSelectedIndex(0);
		}
		else if (e.getSource() == mostrar) {

			resultado.setText(app.mostraInfoTransporte());
		}
		else if(e.getSource() == limparArea) {
			resultado.setText("");
		}
		else if (e.getSource() == fim) {
			System.exit(0); // fecha a aplicação
		}
		else if (e.getSource() == campo1) {
			atualizaCapos();
		}
	}

	/**
	 * Atualiza os campos conforme a selecao dos transportes
	 */
	public void atualizaCapos(){
		if(((String)campo1.getSelectedItem()).contains("1")){
			painelEsquerda.remove(painelCampo11);
			painelEsquerda.remove(painelCampo12);
			painelEsquerda.remove(botoes1);
			painelEsquerda.add(painelCampo10);
			painelEsquerda.add(botoes1);
			revalidate();             // Revalida a janela para atualizar o layout
			repaint();                // Reapinta a janela
		}
		if (((String)campo1.getSelectedItem()).contains("2")){
			painelEsquerda.remove(painelCampo10);  // remove um dos paineis
			painelEsquerda.remove(painelCampo11);  // remove um dos paineis
			painelEsquerda.remove(botoes1);
			painelEsquerda.add(painelCampo12);     // adiciona o painel
			painelEsquerda.add(botoes1);			// adicona o botao
			revalidate();             // Revalida a janela para atualizar o layout
			repaint();                // Reapinta a janela
		}
		if (((String)campo1.getSelectedItem()).contains("3")){

			painelEsquerda.remove(painelCampo10);// Remove o painel atual
			painelEsquerda.remove(painelCampo12);// Remove o painel atual

			painelEsquerda.remove(botoes1);
			painelEsquerda.add(painelCampo11);         // Adiciona o novo painel
			painelEsquerda.add(botoes1);
			revalidate();             // Revalida a janela para atualizar o layout
			repaint();                // Reapinta a janela
		}
		if (((String)campo1.getSelectedItem()).contains("N")){
			painelEsquerda.remove(painelCampo10);
			painelEsquerda.remove(painelCampo11);
			painelEsquerda.remove(painelCampo12);
			revalidate();
			repaint();
		}
	}
	/**
	 * <p>Gera o painel da esquerda</p>
	 * @return o painel da esquerda
	 */
	public JPanel geraPainelEsquerda(){
		painelEsquerda = new JPanel();
		FlowLayout flowEsquerda = new FlowLayout(FlowLayout.RIGHT);
		GridLayout gridEsquerda = new GridLayout(12,1);
		painelEsquerda.setLayout(gridEsquerda);
		painelEsquerda.add(new JLabel("<html><h1>Cadastro de Transporte</h1></html>", JLabel.CENTER));

		JPanel painelCampo1 = new JPanel();
		JPanel painelCampo2 = new JPanel();
		JPanel painelCampo3 = new JPanel();
		JPanel painelCampo4 = new JPanel();
		JPanel painelCampo5 = new JPanel();
		JPanel painelCampo6 = new JPanel();
		JPanel painelCampo7 = new JPanel();
		JPanel painelCampo8 = new JPanel();
		JPanel painelCampo9 = new JPanel();
		painelCampo10 = new JPanel();
		painelCampo11 = new JPanel();
		painelCampo12 = new JPanel();


		painelCampo1.setLayout(flowEsquerda);
		painelCampo2.setLayout(flowEsquerda);
		painelCampo3.setLayout(flowEsquerda);
		painelCampo4.setLayout(flowEsquerda);
		painelCampo5.setLayout(flowEsquerda);
		painelCampo6.setLayout(flowEsquerda);
		painelCampo7.setLayout(flowEsquerda);
		painelCampo8.setLayout(flowEsquerda);
		painelCampo9.setLayout(flowEsquerda);
		painelCampo10.setLayout(flowEsquerda);
		painelCampo12.setLayout(flowEsquerda);


		// campos a srem criados
		String [] tipoTransporte = {"Nada Selecionado",CategoriaCarga.PESSOAS.toString(),CategoriaCarga.CARGA_INANIMADA.toString(),CategoriaCarga.CARGA_VIVA.toString()};
		campo1 = new JComboBox(tipoTransporte);
		painelCampo1.add(new JLabel("Tipo de carga"));
		painelCampo1.add(campo1);
		campo2 = new JTextField(20);
		painelCampo2.add(new JLabel("Código"));
		painelCampo2.add(campo2);
		campo3 = new JTextField(20);
		painelCampo3.add(new JLabel("Cliente"));
		painelCampo3.add(campo3);
		campo4 = new JTextField(20);
		painelCampo4.add(new JLabel("Descrição"));
		painelCampo4.add(campo4);
		campo5 = new JTextField(20);
		painelCampo5.add(new JLabel("Peso"));
		painelCampo5.add(campo5);
		campo6 = new JTextField(20);
		painelCampo6.add(new JLabel("Latitude Origem"));
		painelCampo6.add(campo6);
		campo7 = new JTextField(20);
		painelCampo7.add(new JLabel("Latitude Destino"));
		painelCampo7.add(campo7);
		campo8 = new JTextField(20);
		painelCampo8.add(new JLabel("Longitude Origem"));
		painelCampo8.add(campo8);
		campo9 = new JTextField(20);
		painelCampo9.add(new JLabel("Longitude Destino"));
		painelCampo9.add(campo9);
		campo10 = new JTextField(20);
		painelCampo10.add(new JLabel("Quantide Pessoas"));
		painelCampo10.add(campo10);
		campo11 = new JTextField(20);
		campo12 = new JTextField(20);
		FlowLayout bFlow = new FlowLayout(FlowLayout.RIGHT,8,0);
		JPanel b11 = new JPanel(bFlow);
		JPanel b12 = new JPanel(bFlow);
		b11.add(new JLabel("Graus mínimo"));
		b11.add(campo11);
		b12.add(new JLabel("Graus máximo"));
		b12.add(campo12);
		GridLayout grid = new GridLayout(2,1);
		JPanel botoes = new JPanel(grid);
		botoes.add(b11);
		botoes.add(b12);
		painelCampo11.add(botoes);
		String [] tipoCarga = {"perigosa", "não perigosa"};
		campo13 = new JComboBox<>(tipoCarga);
		painelCampo12.add(new JLabel("Tipo de carga"));
		painelCampo12.add(campo13);


		//adição no painel da esquerda
		painelEsquerda.add(painelCampo1);
		painelEsquerda.add(painelCampo2);
		painelEsquerda.add(painelCampo3);
		painelEsquerda.add(painelCampo4);
		painelEsquerda.add(painelCampo5);
		painelEsquerda.add(painelCampo6);
		painelEsquerda.add(painelCampo7);
		painelEsquerda.add(painelCampo8);
		painelEsquerda.add(painelCampo9);
		//painelEsquerda.add(painelCampo10);

		botoes1 = new JPanel();
		botoes1.setLayout(flowEsquerda);
		salvar = new JButton("Salvar");
		limpar = new JButton("Limpar");
		salvar.setPreferredSize(new Dimension(100,50));
		limpar.setPreferredSize(new Dimension(100,50));
		botoes1.add(salvar);
		botoes1.add(limpar);
		painelEsquerda.add(botoes1);

		return painelEsquerda;
	}

	/**
	 * <p>Gera o painel da esquerda</p>
	 * @return o painel da esquerda
	 */
	public JPanel geraPainelDireita() {
		//criacao do painel da direita
		JPanel painelDireita = new JPanel();
		FlowLayout flowDireita = new FlowLayout(FlowLayout.TRAILING);
		GridLayout gridDireita = new GridLayout(4,1);
		painelDireita.setLayout(gridDireita);


		GridLayout resultadoLayout = new GridLayout(1,1);
		JPanel painelResultado = new JPanel();
		painelResultado.setLayout(resultadoLayout);
		painelDireita.add(new JLabel("<html><h2>Informações<h2></html>", JLabel.CENTER));

		resultado = new JTextArea(70,30);
		JScrollPane painelAreaTexto = new JScrollPane(resultado);
		resultado.setEditable(false);
		painelResultado.add(painelAreaTexto);
		painelDireita.add(painelResultado);


		JPanel botoes2 = new JPanel();
		botoes2.setLayout(flowDireita);
		mostrar = new JButton("Mostrar");
		limparArea = new JButton("Limpar");
		botoes2.add(mostrar);
		botoes2.add(limparArea);
		painelDireita.add(botoes2);

		fim = new JButton("Finaliza");
		fim.setSize(100,50);
		FlowLayout b3 = new FlowLayout(FlowLayout.RIGHT,10,130);
		JPanel botoes3 = new JPanel();
		botoes3.setLayout(b3);

		fim.setPreferredSize(new Dimension(100,50));
		botoes3.add(fim);
		painelDireita.add(botoes3);

		return painelDireita;
	}

}
