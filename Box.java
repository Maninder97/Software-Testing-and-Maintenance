package packer;

/**
 *
 * @author 90051500
 */
public class Box {
    
    
    public Manifest contents;
    public Customer customer;
    public Depot depot; 

    public Box(Customer customer, Depot depot) {
        this.customer = customer;
        this.depot = depot;
        contents = new Manifest();
    }

   
  
    // *This method was also defined below (Now in comments)
    public void addProduct(Product product) {
        if (canFit(product)) {
            contents.addProduct(product, 1);
        }
    }
    
    
    
    public void addProduct(Product product, int quantity) {
        if (canFit(product,quantity)); {
            contents.addProduct(product, quantity);
        }
    }
   
    public String getLabel() {
        StringBuilder label = new StringBuilder();
        label.append(customer);
        label.append("\n");
        label.append(customer.getClosestAddressTo(depot));
        label.append("\n");
        label.append(contents.toString());
        label.append("\n");
        if (this.isFragile()) {
            label.append("FRAGILE\n");
        }
        
        // creating label for heavy boxes
         if (this.isHeavy()) {
            label.append("HEAVY\n");
        }
         
         // Creating label for hazardous items
          if (this.isHazardous()) {
            label.append("HAZARD\n");
        }
        return label.toString();
    }
    
    public String toString() {
        return getLabel();
    }
    
    // To get the weight of the product, "return contents.weight" is changed to "return contents.getTotalWeight" 
    public double getWeight() {
        return contents.getTotalWeight();
    }
    
    /*
    //Refactoring 3
    // The method below is already defined above *
    */
    
    /*public void addProduct(Product product) {
        if (canFit(product)) {
            contents.addProduct(product, 1);
        }
    }
    */
    
    // Capacity of the box changed to 20Kg from 40Kg
    public boolean canFit(Product p) {
        return p.getWeight() <= 20;
    }
    
    public boolean canFit(Product p, int quantity) {
        return (p.getWeight() * quantity) <= 20;
    }
    
    public double remainingCapacity() {
        return 20 - this.getWeight();
    }
    
    public boolean isFragile() {
        return contents.hasFragileItems();
    }
    
    // method created to label hazardous on box
    public boolean isHazardous() {
        return contents.hasHazardousItems();
        
        // "return false" doesnt label "Hazard" on box
        //return false;
    }
    
    //Method created to label Heavy on box if it contains items more than 15Kg.
    public boolean isHeavy() {
        if (contents.getTotalWeight() > 15) {
        return contents.hasHeavyItems();
        }
        return true;
    }
}
