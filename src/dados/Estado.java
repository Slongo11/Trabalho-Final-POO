package dados;

public enum Estado {
	PENDENTE(1,"Pendente"),ALOCADO(2, "Alocado"), TERMINADO(3,"Finalizado"),
	CANCELADO(4,"Cancelado");

	private final int codigo;
	private final String descricao;

	Estado(int codigo,String estado){
		this.codigo = codigo;
		this.descricao = estado;
	}

	/**
	 * <p>Codigo da enum</p>
	 * @return codigo da enum
	 */
	public int getCodigo(){
		return codigo;
	}
	@Override
	public String toString(){
		return descricao;
	}
}
