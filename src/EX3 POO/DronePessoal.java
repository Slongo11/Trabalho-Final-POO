public class DronePessoal extends Drone {
    private int qntMaxPessoas;

    public DronePessoal(int codigo, double custoFixo, double autonomia, int qntMaxPessoas) {
        super(codigo, custoFixo, autonomia);
        this.qntMaxPessoas = qntMaxPessoas;
    }

    @Override
    public double calculaCustoKm() {
        return (super.getCustoFixo() + qntMaxPessoas*2);
    }

    @Override
    public String toString() {
        return "Drone Pessoal - Código: " + super.getCodigo() + " - Custo Fixo: " + super.getCustoFixo() + " - Autonomia: " + super.getAutonomia() + " - Quantidade Máxima de Pessoas: " + qntMaxPessoas + " - Custo por Km: " + this.calculaCustoKm();
    }
}