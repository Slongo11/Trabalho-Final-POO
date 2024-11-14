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
		return "";
	}

	@Override
	public double calculaCustoKm() {
		return (super.getCustoFixo() + qtdMaxPessoas*2);
	}

	@Override
	public String toString() {
		return "Drone Pessoal - Código: " + super.getCodigo() + " - Custo Fixo: " + super.getCustoFixo() + " - Autonomia: " + super.getAutonomia() + " - Quantidade Máxima de Pessoas: " + qtdMaxPessoas + " - Custo por Km: " + this.calculaCustoKm();
	}


}
