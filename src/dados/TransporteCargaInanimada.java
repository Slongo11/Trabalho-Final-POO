package dados;

public class TransporteCargaInanimada extends Transporte {

	private boolean cargaPerigosa;
	public TransporteCargaInanimada(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino, boolean cargaPerigosa) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.cargaPerigosa = cargaPerigosa;
	}
	public boolean eCargaPerigosa() {
		return cargaPerigosa;
	}

	@Override
	public double calculaCusto() {
		Drone d = getDrone();
		if(d != null)
			return d.calculaCustoKm()*calculaKm() + (cargaPerigosa? 500 : 0);
		return 0;
	}

	@Override
	public String geraArmazenavel(){
		return String.format("2;%s;%s;%s;%s\n",super.geraArmazenavel(),cargaPerigosa,getDrone()==null?"null":getDrone().getCodigo(),getSituacao());
	}
	@Override
	public String toString(){

		return String.format("""
						--Carga Inanimada--
						%stipo: %s
						Valor do Transporte: %.2f
						---
						%s
						---
						""",super.toString(),cargaPerigosa ? "Perigosa":"Não Perigosa",calculaCusto(),getDrone() == null ? "Nenhum drone":getDrone().toString());
	}
}
