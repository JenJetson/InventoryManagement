package Model;

public class InhousePart extends Part {

    int machineId;

    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(0, "none", 0.0, 0, 0, 0);

        this.machineId = machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
}
