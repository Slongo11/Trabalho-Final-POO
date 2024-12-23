package dados;

public class DronePessoal extends Drone {

	private int qtdMaxPessoas;

	public DronePessoal(int codigo, double custoFixo, double autonomia, int qntMaxPessoas) {
		super(codigo, custoFixo, autonomia);
		this.qtdMaxPessoas = qntMaxPessoas;
	}

	public int getQtdMaxPessoas() {
		return qtdMaxPessoas;
	}
	@Override
	public String geraArmazenavel() {
		return String.format("1;%s;%s", super.geraArmazenavel(),qtdMaxPessoas);
	}

	@Override
	public double calculaCustoKm() {
		return (getCustoFixo() + qtdMaxPessoas*2);
	}

	@Override
	public String toString() {

		return "\nDrone Pessoal\n"+super.toString() + "Quantidade de pessoas: "+qtdMaxPessoas;
	}


}
