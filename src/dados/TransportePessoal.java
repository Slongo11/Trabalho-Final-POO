package dados;

public class TransportePessoal extends Transporte {

	private int qtdPessoas;
	public TransportePessoal(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino,int qtdPessoas) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.qtdPessoas = qtdPessoas;
	}
	public int getQtdPessoas() {
		return qtdPessoas;
	}
	@Override
	public double calculaCusto() {
		Drone d = getDrone();
		if(d != null)
			return getDrone().calculaCustoKm()*calculaKm() + qtdPessoas*10;
		return 0;
	}
	@Override
	public String geraArmazenavel(){
		return String.format("1;%s;%s\n",super.geraArmazenavel(),qtdPessoas);
	}
	public String toString() {
		return String.format("""
						--Pessoal--
						%sQuantidade de pessoas: %d
						Valor do Transporte: %.2f
						""",super.toString(), qtdPessoas,calculaCusto());
	}
}
