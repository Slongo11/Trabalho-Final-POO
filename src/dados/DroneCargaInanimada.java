package dados;

public class DroneCargaInanimada extends DroneCarga {

	private boolean protecao;


	public boolean eProtegido() {
		return protecao;
	}
	@Override
	public double calculaCustoKm() {
		return 0;
	}

	@Override
	public String geraArmazenavel() {
		return "";
	}
}
