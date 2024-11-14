package dados;

public class DroneCargaInanimada extends DroneCarga {

	private boolean protecao;

	@Override
	public double calculaCustoKm() {
		return 0;
	}
}
