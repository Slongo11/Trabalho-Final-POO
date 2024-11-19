package dados;


public abstract class DroneCarga extends Drone{

	private double pesoMaximo;

	public DroneCarga(int codigo, double custoFixo, double autonomia, double pesoMaximo) {
		super(codigo, custoFixo, autonomia);
		this.pesoMaximo = pesoMaximo;
	}

	@Override
	public double calculaCustoKm() {
		return 0;
	}

	@Override
	public String geraArmazenavel() {
		return "";
	}

	@Override
	public String toString(){
		return String.format("""
				%sPeso Maximo: %.3f
				""",super.toString(), pesoMaximo);
	}
}
