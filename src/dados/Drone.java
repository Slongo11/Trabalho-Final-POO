package dados;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Drone implements Armazenavel{
	private int codigo;
	private double custoFixo;
	private double autonomia;
	//TODO FAZER O DRONE CONHECER OS TRANSPOTES QUE TEM UM LIGAÇÃO
	private ArrayList<Transporte> transporte;

	public Drone(int codigo, double custoFixo, double autonomia) {
		this.codigo = codigo;
		this.custoFixo = custoFixo;
		this.autonomia = autonomia;
		this.transporte = new ArrayList<>();
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
	@Override
	public String toString() {
		return String.format("""
				Código: %d
				CustoFixo: %.2f
				Autonomia: %.2f
				Transporte Relacionados: %d
				""",codigo,custoFixo,autonomia,transporte.size());
	}
}
