package dados;

import java.util.ArrayList;

public abstract class Drone implements Armazenavel{
	private int codigo;
	private double custoFixo;
	private double autonomia;
	private ArrayList<Transporte> transportes;

	public Drone(int codigo, double custoFixo, double autonomia) {
		this.codigo = codigo;
		this.custoFixo = custoFixo;
		this.autonomia = autonomia;
		this.transportes = new ArrayList<>();
	}
	public abstract double calculaCustoKm();

	public void addTransporte(Transporte transporte){
		this.transportes.add(transporte);
	}
	public double getAutonomia() {
		return autonomia;
	}
	public int getCodigo() {
		return codigo;
	}

	public double getCustoFixo() {
		return custoFixo;
	}
	@Override
	public String geraArmazenavel() {
		return String.format("%s;%s;%s",codigo,custoFixo,autonomia);
	}
	@Override
	public String toString() {
		return String.format("""
				Código: %d
				CustoFixo: %.2f
				Autonomia: %.2f
				Transporte Relacionados: %d
				""",codigo,custoFixo,autonomia,transportes.size());
	}
}
