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
public interface ProductInterface {

    public String getType();

    public double getCostPerSqFt();

    public double getLaborPerSqFt();

    @Override
    public String toString();
}
