package dados;

public class DroneCargaViva extends DroneCarga {

	private boolean climatizado;

	public DroneCargaViva(int codigo, double custoFixo, double autonomia, double pesoMaximo, boolean climatizado) {
		super(codigo, custoFixo, autonomia, pesoMaximo);
		this.climatizado = climatizado;
	}

	public boolean eClimatizado() {
		return climatizado;
	}
	@Override
	public double calculaCustoKm() {
		return getCustoFixo() + (climatizado ? 20 : 10);
	}

	@Override
	public String geraArmazenavel() {
		return String.format("3;%s;%s", super.geraArmazenavel(),climatizado);
	}
	@Override
	public String toString() {
		return "\nDrone Carga Viva\n"+super.toString() + "Climatizado: " + (climatizado?"Tem climatização":"Sem climatização");
	}
}
