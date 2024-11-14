
import java.util.ArrayList;

public class Frota {
    private ArrayList<Drone> drones;

    public Frota() {
        drones = new ArrayList<>();
    }

    public boolean  adicionarDrone(Drone drone) {
        for (Drone d : drones) {
            if (d.getCodigo() == drone.getCodigo()) {
                System.out.println("Drone já cadastrado");
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

    public String listarDrones() {
        String lista = "";
        for (Drone d : drones) {
            lista += d.toString() + "\n";
        }
        return lista;
    }
}
