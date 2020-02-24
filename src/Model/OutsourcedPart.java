package Model;

public class OutsourcedPart extends Part {

    String companyName;

    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(0, "none", 0.0, 0, 0, 0);

        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
