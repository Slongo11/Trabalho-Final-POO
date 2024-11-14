package dados;

public class DroneCargaViva extends DroneCarga {

	private boolean climatizado;

	@Override
	public double calculaCustoKm() {
		return 0;
	}

	@Override
	public String geraArmazenavel() {
		return "";
	}
}
