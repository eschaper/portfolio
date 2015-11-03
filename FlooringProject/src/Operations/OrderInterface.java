/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

/**
 *
 * @author apprentice
 */
public interface OrderInterface {

    @Override
    public String toString();

    public void setCustLastName(String custLastName);

    public void setCustFirstName(String custFirstName);

    public void setArea(double area);

    public void setState(StateInterface state);

    public void setProduct(ProductInterface product);

    public int getOrderNum();

    public String getCustLastName();

    public String getCustFirstName();

    public double getArea();

    public StateInterface getState();

    public ProductInterface getProduct();

    public double getMaterialCostTotal();

    public double getLaborCostTotal();

    public double getTaxTotal();

    public String getProjectTotal();

}
