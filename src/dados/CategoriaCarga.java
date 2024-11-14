package dados;

public enum CategoriaCarga {
	PESSOAS(1,"Pessoas"),CARGA_INANIMADA(2,"Carga inanimada"), CARGA_VIVA(3,"Carga viva");

	private final int codigo;
	private final String descricao;

	 CategoriaCarga(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * <p>pega o codigo da enum</p>
	 * @return o codigo da enum
	 */
	public int getCodigo() {
		return codigo;
	}
	@Override
	public String toString() {
		return String.format("%d-%s",codigo,descricao);
	}
}
