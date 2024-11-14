package dados;

public abstract class Transporte implements Armazenavel {

	private int numero;
	private String nomeCliente;
	private String descricao;
	private double peso;
	private double latitudeOrigem;
	private double latitudeDestino;
	private double longitudeOrigem;
	private double longitudeDestino;
	private Estado situacao;
	private Drone drone;

	public Transporte(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino) {
		this.numero = numero;
		this.nomeCliente = nomeCliente;
		this.descricao = descricao;
		this.peso = peso;
		this.latitudeOrigem = latitudeOrigem;
		this.latitudeDestino = latitudeDestino;
		this.longitudeOrigem = longitudeOrigem;
		this.longitudeDestino = longitudeDestino;
		this.situacao = Estado.PENDENTE;
		this.drone = null;
	}
	/**
	 * <p>calcula o custo do transporte do transporte</p>
	 * @return o custo total do transporte
	 */
	public abstract double calculaCusto();

	/**
	 * <p>Calcula o km de distancia com base
	 * da longitude e latitude inicial e final forneciada</p>
	 * @return a quilometragem referente as informacoes
	 */
	public double calculaKm(){
		var r = 6371; // Raio da terra em km
		var dLat = deg2rad(latitudeDestino-latitudeOrigem);
		var dLon = deg2rad(longitudeDestino-longitudeOrigem);
		var a =
				Math.sin(dLat/2) * Math.sin(dLat/2) +
						Math.cos(deg2rad(latitudeOrigem)) * Math.cos(deg2rad(latitudeDestino)) *
								Math.sin(dLon/2) * Math.sin(dLon/2)
				;
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		var d = r * c; // Distancia em km
		return d;
	}

	/**
	 * <p>Converte graus em radianos</p>
	 * @param deg graus a serem calculados.
	 * @return o radiano.
	 */
	private double deg2rad(double deg) {
		return deg * Math.PI / 180.0;
	}

	public int getNumero() {
		return numero;
	}

	/**
	 * <p>Atualiza o Status do transporte</p>
	 * @param status a ser atualizado
	 */
	public void atualizaStatus(Estado status){
		if(situacao == Estado.PENDENTE){
			situacao = status;
		}
		if(situacao == Estado.ALOCADO && status != Estado.PENDENTE){
			situacao = status;
		}
	}

	/**
	 * <p>Situacao em que se encontra o transporte</p>
	 * @return o estado do transporte
	 */
	public Estado getSituacao() {
		return situacao;
	}

	/**
	 * <p>Adiciona o drone que vai fazer o pedido</p>
	 * @param drone a ser alocado
	 */
	public void adicionaDrone(Drone drone){
		if(situacao == Estado.PENDENTE){
			if(drone != null){
				this.drone = drone;
				atualizaStatus(Estado.ALOCADO);
			}
		}

	}

	@Override
	public String geraArmazenavel() {
		return String.format("%s;%s;%s;%s;%s;%s;%s;%s",numero,nomeCliente,descricao,peso,latitudeOrigem,latitudeOrigem,longitudeOrigem,longitudeOrigem);
	}

	@Override
	public String toString(){
		return String.format("""
							Codigo: %d
							Nome: %s
							Descricao: %s
							Peso: %.3f
							Latitude Origem: %.5f
							Latitude Destino: %.5f
							Longitude Origem: %.5f
							Longitude Destino: %.5f
							Distancia: %.3f KM
							Situacao: %s
							Drone: %s
							""",numero,nomeCliente,descricao,peso, latitudeOrigem,
				latitudeDestino,longitudeOrigem,longitudeDestino,calculaKm(),
				situacao.toString(),drone == null ? "Nenhum drone":"Drone codigo:");
	}

}
