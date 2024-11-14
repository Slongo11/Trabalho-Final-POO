package dados;
import java.util.*;

public class ListaDrones {
	ArrayList<Drone> drones;
	public ListaDrones() {
		drones = new ArrayList<>();
	}

	/**
	 * Adiciona drones
	 * @param drone o drone
	 * @return caso adicione
	 */
	public boolean adicionarDrone(Drone drone) {
		return drones.add(drone);
	}
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
		}else if(CategoriaCarga.PESSOAS == categoria){
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
		throw new Exception("Nenhum drone encontrado");
	}

	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar carga Inanimada</p>
	 * @param distancia a distancia a ser percorida
	 * @return o drone
	 * @throws Exception caso nao enecontre nenhum drone
	 */
	private Drone capacitadoDroneCargaInanimada(double distancia) throws Exception{
		for (Drone drone : drones) {
			if(drone instanceof DroneCargaInanimada && drone.getAutonomia() >= distancia){
				return drone;
			}
		}

		throw new Exception("Nenhum drone encontrado");
	}
	/**
	 * <p>Pega um drone capacitado em fazer o trabalho de levar pessoas</p>
	 * @param distancia a distancia a ser percorida
	 * @return o drone
	 * @exception Exception caso nao encotre nenhum drone
	 */
	private Drone capacitadoDroneCargaViva(double distancia) throws Exception {
		for (Drone drone : drones) {
			if(drone instanceof DroneCargaViva && drone.getAutonomia() >= distancia){
				return drone;
			}
		}
		throw new Exception("Nenhum drone encontrado");
	}


}
