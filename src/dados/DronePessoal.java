package dados;

public class DronePessoal extends Drone {

	private int qtdMaxPessoas;

	@Override
	public double calculaCustoKm() {
		return 0;
	}

	@Override
	public String geraArmazenavel() {
		return "";
	}
}
