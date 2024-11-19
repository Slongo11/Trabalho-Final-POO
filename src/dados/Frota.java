package dados;
import java.util.*;

public class Frota implements Agrupavel{
	private ArrayList<Drone> drones;

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
		StringBuilder sb = new StringBuilder();
		for (Drone d : drones) {
			sb.append(d.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * <p>Copia dos drones a serem usados</p>
	 * @return a lista de drones
	 */
	public ArrayList<Drone> getDrones() {

		return (ArrayList<dados.Drone>) drones.clone();
	}
	/**
	 * <p>Procura algum drone capacitado segundo as informacoes mostradas</p>
	 * @param categoria da carga a ser levada
	 * @param distancia a ser percorida
	 * @param pessoa quantidade de pessosas a ser transportada
	 * @return drone encontrado
	 * @throws Exception caso a catergora esteja errada
	 */
	public Drone capacitado(CategoriaCarga categoria,double distancia, int pessoa) throws Exception{
		if(categoria == CategoriaCarga.PESSOAS) {
			return capacitadoDronePessoal(distancia, pessoa);
		}
		throw new Exception("Categoria invalida");
	}

	/**
	 * <p>Procura algum drone capacitado segundo as informacoes mostradas</p>
	 * @param categoria da carga a ser levada
	 * @param distancia a ser percorida
	 * @return drone encontrado
	 * @throws Exception caso a catergora esteja errada
	 */
	public Drone capacitado(CategoriaCarga categoria,double distancia) throws Exception{
		if(CategoriaCarga.CARGA_VIVA == categoria){
			return capacitadoDroneCargaViva(distancia);
		}else if(CategoriaCarga.CARGA_INANIMADA == categoria){
			return capacitadoDroneCargaInanimada(distancia);
		}
		throw new Exception("Categoria invalida");
	}

	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar pessoas</p>
	 * @param distancia a distancia a ser percorida
	 * @param peossas a quantidade de pessoas
	 * @return o drone
	 * @exception Exception caso nao encotre nenhum drone
	 */
	private Drone capacitadoDronePessoal(double distancia, int peossas) throws Exception{
		for (Drone drone : drones) {
			if(drone instanceof DronePessoal && drone.getAutonomia() >= distancia && ((DronePessoal)drone).getQtdMaxPessoas() >= peossas){
				return drone;
			}
		}
		return null;
	}

	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar carga Inanimada</p>
	 * @param distancia a distancia a ser percorida
	 * @return o drone
	 */
	private Drone capacitadoDroneCargaInanimada(double distancia){
		for (Drone drone : drones) {
			if(drone instanceof DroneCargaInanimada && drone.getAutonomia() >= distancia){
				return drone;
			}
		}

		return null;
	}
	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar pessoas</p>
	 * @param distancia a distancia a ser percorida
	 * @return o drone
	 */
	private Drone capacitadoDroneCargaViva(double distancia) {
		for (Drone drone : drones) {
			if(drone instanceof DroneCargaViva && drone.getAutonomia() >= distancia){
				return drone;
			}
		}
		return null;
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
