package dados;

public class TransporteCargaViva extends Transporte {

	private double temperaturaMinima;
	private double temperaturaMaxima;

	public TransporteCargaViva(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino, double temperaturaMinima, double temperaturaMaxima) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.temperaturaMaxima = temperaturaMaxima;
		this.temperaturaMinima = temperaturaMinima;
	}
	@Override
	public double calculaCusto() {
		int custo = 0;
		if(temperaturaMaxima - temperaturaMinima > 10)
			custo+= 500;
		return custo;
	}

	@Override
	public String toString(){

		return String.format("""
					--Carga Viva--
					%stemperaturaMinima: %.2f
					temperaturaMaxima: %.2f
					""",super.toString(),temperaturaMinima,temperaturaMaxima);
	}
}
