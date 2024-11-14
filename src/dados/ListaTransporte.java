package dados;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class ListaTransporte {
	private ArrayList<Transporte> listaTransporte;
	private Queue<Transporte> filaDeTransporte;
	public ListaTransporte() {
		listaTransporte = new ArrayList<>();
		filaDeTransporte = new ArrayDeque<>();
	}

	/**
	 * <p>Cadastra um transporte </p>
	 * @param t transporte a ser cadastrado
	 * @return
	 */
	public boolean cadastraTransporte(Transporte t) throws Exception{
		if(!(listaTransporte.stream().allMatch(n -> n.getNumero() != t.getNumero()))) {
			throw new Exception("Não foi possivel cadastrar o transporte código repetido.");
		}
		filaDeTransporte.add(t);
		return listaTransporte.add(t);
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
