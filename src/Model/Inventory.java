package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventory {

    public static ObservableList<Part> partsList = FXCollections.observableArrayList();
    public static ObservableList<Product> productsList = FXCollections.observableArrayList();
    public static int index = 1;

    public static void addPart(Part part){
        partsList.add(part);
        index++;
    }
    public static void addProduct(Product product){
        productsList.add(product);
    }
    public static Part lookupPart(int partId){
        return lookupPart(partId);
    }
    public static Product lookupProduct(int productId){
        return lookupProduct(productId);
    }
    public static int getIndex(){
        return index;
    }

    public ArrayList lookupPart(String partName){
        ArrayList <Part> matchedParts = new ArrayList<>(); //create an array to hold the matches
        for (Part part : partsList){  //use enhanced for loop to save matches
            if (part.getName() == partName){
                matchedParts.add(part);
            }
        }
        return matchedParts;
    }
    public ArrayList lookupProduct(String productName){
        ArrayList <Product> matchedProducts = new ArrayList<>();
            for (Product product : productsList){
                if (product.getName() == productName){
                    matchedProducts.add(product);
                }
            }
        return matchedProducts;
    }
    public static ObservableList<Part> getAllParts(){
        return partsList;
    }
    public static ObservableList<Product> getAllProducts(){
        return productsList;
    }
    public void updateProduct(int index, Product newProduct){
        for (Product product : productsList ){
            if (product.getId() == index){
                product = newProduct;
            }else{
                System.out.println("no Match");
            }
        }
    }
    public static void updatePart(int index, Part selectedPart){
        for (Part part : partsList ){
            if (part.getId() == index){
                part = selectedPart;
            } else {
                System.out.println("no match");
            }
        }
    }
    public boolean deletePart(Part selectedPart){
        for (Part part : partsList){
            if (part == selectedPart){
                partsList.remove(part);
            }
        }
        return true; //fixme not sure on this
    }
    public boolean deleteProduct(Product selectedProduct){
        for (Product product : productsList) {
            if (product == selectedProduct){
                productsList.remove(selectedProduct);
            }
        }
        return true;  //fixme not sure on this.
    }


}