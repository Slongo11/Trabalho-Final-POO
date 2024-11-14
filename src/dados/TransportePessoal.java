package dados;

public class TransportePessoal extends Transporte {

	private int qtdPessoas;
	public TransportePessoal(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino,int qtdPessoas) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.qtdPessoas = qtdPessoas;
	}
	@Override
	public double calculaCusto() {
		return qtdPessoas*10;
	}

	public String toString() {
		return String.format("""
						--Pessoal--
						%sQuantidade de pessoas: %d
						""",super.toString(), qtdPessoas);
	}
}
