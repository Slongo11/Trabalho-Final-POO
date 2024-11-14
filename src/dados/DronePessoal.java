package dados;

public class DronePessoal extends Drone {

	private int qtdMaxPessoas;

	@Override
	public double calculaCustoKm() {
		return 0;
	}

	public int getQtdMaxPessoas() {
		return qtdMaxPessoas;
	}

	@Override
	public String geraArmazenavel() {
		return "";
	}
}
