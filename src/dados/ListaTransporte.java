package dados;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class ListaTransporte implements Agrupavel {
	private ArrayList<Transporte> listaTransporte;
	private Queue<Transporte> filaDeTransporte;
	public ListaTransporte() {
		listaTransporte = new ArrayList<>();
		filaDeTransporte = new ArrayDeque<>();
	}

	/**
	 * <p>Cadastra um transporte </p>
	 *  @param t transporte a ser cadastrado
	 * 	@return verdadeiro se for bem sucedido
	 * @throws Exception que não foi possível cadastrar
	 */
	public boolean cadastraTransporte(Transporte t) throws Exception{
		if(!(listaTransporte.stream().allMatch(n -> n.getNumero() != t.getNumero()))) {
			throw new Exception("Não foi possivel cadastrar o transporte código repetido.");
		}
		filaDeTransporte.add(t);
		listaTransporte.add(t);
		sort();
		return true;
	}
	private void sort() {
		for (int i = 1; i < listaTransporte.size(); i++) {
			Transporte cod = listaTransporte.get(i);
			int j = i - 1;
			while (j >= 0 && listaTransporte.get(j).getCodigo() > cod.getCodigo()) {
				listaTransporte.set(j + 1, listaTransporte.get(j));
				j = j - 1;
			}
			listaTransporte.set(j + 1, cod);
		}
	}
	public Queue<Transporte> getFilaDeTransporte() {
		return filaDeTransporte;
	}

	/**
	 * <p>Busca o transporte pelo numero cadastrado.</p>
	 * @param numero a ser procurado.
	 * @return o transporte com o numero ou null caso nao encontrar.
	 */
	public Transporte buscaPorNumero(int numero){
		for(Transporte t : listaTransporte){
			if(t.getNumero() == numero){
				return t;
			}
		}
		return null;
	}

	@Override
	public String getCsvFormat() {
		StringBuilder str = new StringBuilder();
		for (Transporte t : listaTransporte) {
			if(t instanceof TransporteCargaInanimada){
				str.append(t.geraArmazenavel());
			}else if(t instanceof TransportePessoal){
				str.append(t.geraArmazenavel());
			}else{
				str.append(t.geraArmazenavel());
			}
		}
		return str.toString();
	}

	@Override
	public String toString(){
		if(listaTransporte.isEmpty()) {
			return "Nada cadastrado.";
		}
		StringBuilder str = new StringBuilder("========ListaTransporte=======\n");
		boolean add= false;
		for(Transporte t : listaTransporte) {
			if(add) {
				str.append("---------------------------\n");
			}
			str.append(t.toString()).append("\n");
				add= true;
		}
		str.append("=========================\n");
		return str.toString();
	}
}
