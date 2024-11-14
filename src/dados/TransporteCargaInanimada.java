package dados;

public class TransporteCargaInanimada extends Transporte {

	private boolean cargaPerigosa;
	public TransporteCargaInanimada(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino, boolean cargaPerigosa) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.cargaPerigosa = cargaPerigosa;
	}
	@Override
	public double calculaCusto() {
		return cargaPerigosa? 500 : 0;
	}

	@Override
	public String geraArmazenavel(){
		return String.format("2;%s;%s\n",super.geraArmazenavel(),cargaPerigosa);
	}
	@Override
	public String toString(){

		return String.format("""
						--Carga Inanimada--
						%stipo: %s
						""",super.toString(),cargaPerigosa ? "Perigosa":"Não Perigosa");
	}
}
