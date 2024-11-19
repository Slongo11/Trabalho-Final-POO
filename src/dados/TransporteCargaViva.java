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
			custo+= 1000;
		Drone d = getDrone();
		if(d != null)
			return getDrone().calculaCustoKm()*calculaKm() + custo;

		return 0;
	}
	@Override
	public String geraArmazenavel(){
		return String.format("3;%s;%s;%s\n",super.geraArmazenavel(),temperaturaMinima,temperaturaMaxima);
	}

	@Override
	public String toString(){

		return String.format("""
					--Carga Viva--
					%sTemperatura Minima: %.2f
					Temperatura Maxima: %.2f
					Valor do Transporte: %.2f
					""",super.toString(),temperaturaMinima,temperaturaMaxima,calculaCusto());
	}
}
