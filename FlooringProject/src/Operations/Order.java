package Operations;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Emma/James
 */
public class Order implements OrderInterface {
    private int orderNum;
    private String custLastName;
    private String custFirstName;
    private double area;
    private StateInterface state;
    private ProductInterface product;
    
    public Order(int orderNum, String custLastName, String custFirstName, 
            double area, StateInterface tax, ProductInterface product) {
        this.orderNum = orderNum;
        this.custLastName = custLastName;
        this.custFirstName = custFirstName;
        this.area = area;
        this.state = tax;
        this.product = product;
    }
    
    @Override
    public String toString() {
        String str;
        
        str = "Order #" + this.orderNum + ". " + this.custLastName + ", " 
                + this.custFirstName + "\nArea: " + this.area + " sqft. Product: " 
                + this.product.getType() + ".\nState: " + this.state.getStateName() 
                + ". Total: " + this.getProjectTotal();
       
        return str;
    }
    

    @Override
    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    @Override
    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    @Override
    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public void setState(StateInterface state) {
        this.state = state;
    }

    @Override
    public void setProduct(ProductInterface product) {
        this.product = product;
    }

    @Override
    public int getOrderNum() {
        return orderNum;
    }

    @Override
    public String getCustLastName() {
        return custLastName;
    }

    @Override
    public String getCustFirstName() {
        return custFirstName;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public StateInterface getState() {
        return state;
    }

    @Override
    public ProductInterface getProduct() {
        return product;
    }

    @Override
    public double getMaterialCostTotal() {
        
        return this.area * this.product.getCostPerSqFt();
        
    }

    @Override
    public double getLaborCostTotal() {
        
        return this.area * this.product.getLaborPerSqFt();
    }

    @Override
    public double getTaxTotal() {
        
        return (this.getMaterialCostTotal() + this.getLaborCostTotal()) 
                * this.state.getTaxRate() / 100;
        
    }

    @Override
    public String getProjectTotal() {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        double total;
        
        total = this.getLaborCostTotal() 
                + this.getMaterialCostTotal() 
                + this.getTaxTotal();
 
        return n.format(total);
       
    }
    
    
}
