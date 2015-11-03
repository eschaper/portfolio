package Operations;

/**
 *
 * @author Emma/James
 */
public class Product implements ProductInterface {
    private String type;
    private double costPerSqFt;
    private double laborPerSqFt;

    public Product(String type, double costPerSqFt, double laborPerSqFt) {
        this.type = type;
        this.costPerSqFt = costPerSqFt;
        this.laborPerSqFt = laborPerSqFt;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getCostPerSqFt() {
        return costPerSqFt;
    }

    @Override
    public double getLaborPerSqFt() {
        return laborPerSqFt;
    }
    
    @Override
    public String toString() {
        return "Type: " + this.getType() 
                + "\nCost per sq ft: " + this.getCostPerSqFt() 
                + "\nLabor cost per sq ft: "+ this.getLaborPerSqFt();
    }
    
    
    
}
