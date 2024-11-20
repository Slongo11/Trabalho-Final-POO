package dados;

public class DroneCargaInanimada extends DroneCarga {

	private boolean protecao;

	public DroneCargaInanimada(int codigo, double custoFixo, double autonomia, double pesoMaximo, boolean protecao) {
		super(codigo, custoFixo, autonomia, pesoMaximo);
		this.protecao = protecao;
	}


	public boolean eProtegido() {
		return protecao;
	}
	@Override
	public double calculaCustoKm() {
		return getCustoFixo() + (protecao ? 10:5);
	}

	@Override
	public String geraArmazenavel() {
		return String.format("2;%s;%s",super.geraArmazenavel(),protecao);
	}
	@Override
	public String toString() {
		return "\nDrone Carga Inanimada\n"+ super.toString() + "Protegido: " + (protecao?"Tem proteção":"Sem proteção");
	}
}
