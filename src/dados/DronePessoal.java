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
		return String.format("%s;%s", super.geraArmazenavel(),qtdMaxPessoas);
	}

	@Override
	public double calculaCustoKm() {
		return (super.getCustoFixo() + qtdMaxPessoas*2);
	}

	@Override
	public String toString() {

		return String.format("""
					========================
					Drone Pessoal
					Código: %d
					Custo Fixo: %.2f
					Autonomia: %.2f
					Quantidade Máxima de Pessoas: %d
					Custo por Km: %.2f
					========================
					""",getCodigo(),getCustoFixo(),getAutonomia(),qtdMaxPessoas, calculaCustoKm());
	}


}
