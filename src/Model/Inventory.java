package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventory {

    public static ObservableList<Part> partsList = FXCollections.observableArrayList();
    public static ObservableList<Product> productsList = FXCollections.observableArrayList();
    public static int partId = 1;  // autogenerates the part ID
    public static int productId = 1;  //autogenerates the product ID

    public static void addPart(Part part){
        partsList.add(part);
        partId++;
    }
    public static void addProduct(Product product){
        productsList.add(product);
        productId++;
    }
    public static Part lookupPart(int partId){
        return lookupPart(partId);
    }
    public static Product lookupProduct(int productId){  //required for final
        return lookupProduct(productId);
    }
    public static int getIndex(){
        return partId;
    }
    public static int getProductId(){
        return productId;
    }

    public static ArrayList lookupPart(String partName){
        ArrayList <Part> matchedParts = new ArrayList<>(); //create an array to hold the matches
        for (Part part : partsList){  //use enhanced for loop to save matches
            if (part.getName() == partName){
                matchedParts.add(part);
            }
        }
        return matchedParts;
    }
    public static ObservableList lookupProduct(String productName){
       ObservableList <Product> matchedProducts = FXCollections.observableArrayList();
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