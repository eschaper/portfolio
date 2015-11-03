package Operations;

/**
 *
 * @author Emma/James
 */
public class State implements StateInterface {
    private String stateName;
    private double taxRate;

    public State(String state, double taxRate) {
        this.stateName = state;
        this.taxRate = taxRate;
    }

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public double getTaxRate() {
        return taxRate;
    }
    
    @Override
    public String toString() {
        return "State abbreviation: " + this.stateName 
                + "\nTax rate: " + this.taxRate;
    }
   
}
