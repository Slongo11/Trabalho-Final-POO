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
		return 0;
	}

	@Override
	public String geraArmazenavel() {
		return "";
	}
}
