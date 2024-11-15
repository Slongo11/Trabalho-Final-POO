package telas;

import aplicacao.ACMEAirDrones;
import dados.Drone;
import dados.DronePessoal;
import dados.Frota;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class TelaCadastroDrone extends JFrame implements ActionListener {

    private JTextField campo;
    private JTextField campo2;
    private JTextField campo3;
    private JTextField campo4;
    private JTextArea area;
    private ACMEAirDrones app;

    public TelaCadastroDrone(ACMEAirDrones app) {
        // Criação da janela
        super("ACME Drone Control");
        Font f_padrao = new Font("Arial", Font.BOLD, 12);
        this.app = app;

        // Criação do painel mais externo
        JPanel painel_externo = new JPanel();
        BoxLayout flowPadrao = new BoxLayout(painel_externo, BoxLayout.X_AXIS);
        painel_externo.setLayout(flowPadrao);

        // Campo de preenchimento
        JPanel preenchimento = new JPanel();
        BoxLayout boxPadrao = new BoxLayout(preenchimento, BoxLayout.Y_AXIS);
        preenchimento.setLayout(boxPadrao);
        preenchimento.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Caixas de texto
        // Código
        JPanel codigo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Digite o código do drone:");
        label.setFont(f_padrao);
        campo = new JTextField(20);
        codigo.add(label);
        codigo.add(campo);
        preenchimento.add(codigo);

        // Custo fixo
        JPanel custoFixo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label2 = new JLabel("Digite o custo fixo de voo do drone:");
        label2.setFont(f_padrao);
        campo2 = new JTextField(20);
        custoFixo.add(label2);
        custoFixo.add(campo2);
        preenchimento.add(custoFixo);

        // Autonomia 
        JPanel autonomia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label3 = new JLabel("Digite a autonomia de voo do drone em horas:");
        label3.setFont(f_padrao);
        campo3 = new JTextField(20);
        autonomia.add(label3);
        autonomia.add(campo3);
        preenchimento.add(autonomia);

        // Quantidade máxima de pessoas
        JPanel qntMax = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label4 = new JLabel("Digite quantidade máxima de pessoas que o drone pode transportar:");
        label4.setFont(f_padrao);
        campo4 = new JTextField(20);
        qntMax.add(label4);
        qntMax.add(campo4);
        preenchimento.add(qntMax);

        // Área de texto
        area = new JTextArea(30, 30);
        area.setFont(new Font("Arial", Font.PLAIN, 12));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        // Botões
        JPanel botoes = new JPanel();
        JButton cadastrar = new JButton("Cadastrar");
        JButton limpar = new JButton("Limpar");
        JButton listar = new JButton("Listar");
        JButton sair = new JButton("Sair");
        
        // Adiciona eventos
        cadastrar.addActionListener(this);
        limpar.addActionListener(this);
        listar.addActionListener(this);
        sair.addActionListener(this);

        botoes.add(cadastrar);
        botoes.add(limpar);
        botoes.add(listar);
        botoes.add(sair);
        preenchimento.add(botoes);

        // Adicionando os componentes ao painel externo
        painel_externo.add(preenchimento);
        painel_externo.add(new JScrollPane(area));

        // Configurações da janela
        getContentPane().add(painel_externo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Sair" -> System.exit(0);
                
            case "Cadastrar" -> {
                try {
                    int cod = Integer.parseInt(campo.getText());
                    double custo = Double.parseDouble(campo2.getText());
                    double aut = Double.parseDouble(campo3.getText());
                    int qnt = Integer.parseInt(campo4.getText());
                    Drone drone = new DronePessoal(cod, custo, aut, qnt);
                    if(app.cadastrarDrone(drone)){
                        area.setText("Drone cadastrado com sucesso!");
                    } else {
                        area.setText("Drone já cadastrado com esse código.");
                    }
                } catch (NumberFormatException ex) {
                    area.setText("Erro ao cadastrar drone. Verifique os dados informados.");
                }
            }

            case "Limpar" -> {
                campo.setText("");
                campo2.setText("");
                campo3.setText("");
                campo4.setText("");
                area.setText("");
            }

            case "Listar" -> // Exemplo: Atualiza a área de texto com a lista de drones
                area.setText(app.mostraInfoDroneDrone());

            default -> {
            }
        }
    }
}
