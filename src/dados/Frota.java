package dados;
import java.util.*;

public class Frota implements Agrupavel{
	private final ArrayList<Drone> drones;

	public Frota() {
		drones = new ArrayList<>();
	}

	/**
	 * <p>Adiciona drones</p>
	 * @param drone o drone
	 * @return caso for bem sucedido retorna vedaddeiro
	 */
	public boolean adicionarDrone(Drone drone) {
		for (Drone d : drones) {
			if (d.getCodigo() == drone.getCodigo()) {
				return false;
			}
		}
		drones.add(drone);
		this.sort();
		return true;
	}

	/**
	 * <p>Procura o drone por meio do codigo</p>
	 * @param codigo do drone as ser procurado
	 * @return o drone com o codigo
	 */
	public Drone buscaDrone(int codigo) {
		return drones.stream()
				.filter(d -> d.getCodigo() == codigo)
				.findFirst()
				.orElse(null);
	}

	private void sort() {
		for (int i = 1; i < drones.size(); i++) {
			Drone cod = drones.get(i);
			int j = i - 1;
			while (j >= 0 && drones.get(j).getCodigo() > cod.getCodigo()) {
				drones.set(j + 1, drones.get(j));
				j = j - 1;
			}
			drones.set(j + 1, cod);
		}
	}

	@Override
	public String toString() {
		if(drones.isEmpty()) {
			return "Nada cadastrado.";
		}
		StringBuilder str = new StringBuilder("========ListaDrones=======\n");
		boolean add= false;
		for(Drone d : drones) {
			if(add) {
				str.append("---------------------------\n");
			}
			str.append(d.toString()).append("\n");
			add= true;
		}
		str.append("=========================\n");
		return str.toString();
	}


	/**
	 * <p>Procura algum drone capacitado segundo as informacoes mostradas</p>
	 * @param categoria da carga a ser levada
	 * @param distancia a ser percorida
	 * @param pessoa quantidade de pessosas a ser transportada
	 * @return drone encontrado ou null
	 */
	public Queue<Drone> capacitado(CategoriaCarga categoria, double distancia, int pessoa){
		if(categoria == CategoriaCarga.PESSOAS) {
			return capacitadoDronePessoal(distancia, pessoa);
		}
		return null;
	}

	/**
	 * <p>Procura algum drone capacitado segundo as informacoes mostradas</p>
	 * @param categoria da carga a ser levada
	 * @param distancia a ser percorida
	 * @return drone encontrado
	 */
	public Queue<Drone> capacitado(CategoriaCarga categoria,double distancia){
		if(CategoriaCarga.CARGA_VIVA == categoria){
			return capacitadoDroneCargaViva(distancia);
		}else if(CategoriaCarga.CARGA_INANIMADA == categoria){
			return capacitadoDroneCargaInanimada(distancia);
		}
		return null;
	}

	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar pessoas</p>
	 * @param distancia a distancia a ser percorida
	 * @param peossas a quantidade de pessoas
	 * @return o drones
	 */
	private Queue<Drone> capacitadoDronePessoal(double distancia, int peossas){
		Queue<Drone> dronePessal = new LinkedList<>();
		for (Drone drone : drones) {
			if(drone instanceof DronePessoal && drone.getAutonomia() >= distancia && ((DronePessoal)drone).getQtdMaxPessoas() >= peossas){
				dronePessal.add(drone);
			}
		}
		return dronePessal;
	}

	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar carga Inanimada</p>
	 * @param distancia a distancia a ser percorida
	 * @return os drones
	 */
	private Queue<Drone> capacitadoDroneCargaInanimada(double distancia){
		Queue<Drone> droneCarga = new LinkedList<>();
		for (Drone drone : drones) {
			if(drone instanceof DroneCargaInanimada && drone.getAutonomia() >= distancia){
				droneCarga.add(drone);
			}
		}

		return droneCarga;
	}
	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar pessoas</p>
	 * @param distancia a distancia a ser percorida
	 * @return o drone
	 */
	private Queue<Drone> capacitadoDroneCargaViva(double distancia) {
		Queue<Drone> droneCarga = new LinkedList<>();
		for (Drone drone : drones) {
			if(drone instanceof DroneCargaViva && drone.getAutonomia() >= distancia){
				droneCarga.add(drone);
			}
		}
		return droneCarga;
	}

	@Override
	public String getCsvFormat() {
		StringBuilder sb = new StringBuilder();
		for (Drone drone : drones) {
			sb.append(drone.geraArmazenavel());
			sb.append("\n");
		}
		return sb.toString();
	}
}
