package Model;

import static Model.Inventory.partsList;

public class OutsourcedPart extends Part {

    private String companyName;

    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static void updatePart(int index, Part newPart, String companyName) {
        Inventory.updatePart(index, newPart);
    }


}
