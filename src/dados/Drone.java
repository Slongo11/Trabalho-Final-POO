package dados;

import java.util.Collection;

public abstract class Drone implements Armazenavel{
	private int codigo;
	private double custoFixo;
	private double autonomia;

	public Drone(int codigo, double custoFixo, double autonomia) {
		this.codigo = codigo;
		this.custoFixo = custoFixo;
		this.autonomia = autonomia;
	}
	public abstract double calculaCustoKm();

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
}
