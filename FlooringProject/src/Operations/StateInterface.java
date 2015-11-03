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
public interface StateInterface {
    
    public String getStateName();
    
    public double getTaxRate();
    
    @Override
    public String toString();
    
}
