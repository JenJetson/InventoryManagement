package Model;

import static Model.Inventory.partsList;
import static Model.Inventory.productsList;

public class InhousePart extends Part {

    private int machineId;

    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);

        this.machineId = machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
    public static void addPart(Part part) {
        partsList.add(part);
    }
    public static void addProduct(Product product) {
        productsList.add(product);
    }

    public static void updatePart(int index, Part updatePart, int machineId){
        for (Part part : partsList){
            if (part.getId()==index){
                part = updatePart;
            }
        }

    }



}
