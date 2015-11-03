package Operations;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Emma/James
 */
public class OrderList {

    private HashMap<String, ArrayList<OrderInterface>> orders = new HashMap<>();
    private ArrayList<StateInterface> states = new ArrayList<>();
    private ArrayList<ProductInterface> products = new ArrayList<>();
    private String todaysDate;

    // method used for jUnit testing
    public void populate() {
        String date;
        ArrayList<OrderInterface> order = new ArrayList<>();
        OrderInterface ord1, ord2;

        date = "09042015";
        ord1 = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        ord2 = new Order(42, "Last", "First", 75,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        order.add(ord2);
        order.add(ord1);

        orders.put(date, order);

        states.add(new State("MN", 7.2));
        states.add(new State("WI", 9));

        products.add(new Product("Wood", 32, 45));
        products.add(new Product("Laminate", 10, 5));

    }

    public boolean dateExist(String date) {
        boolean exists = false;
        Set<String> keys = orders.keySet();

        for (String s : keys) {
            if (s.equals(date)) {
                exists = true;
            }
        }

        return exists;
    }

    public boolean orderExist(String date, int orderNum) {
        ArrayList<OrderInterface> currentOrders = orders.get(date);
        boolean exists = false;

        for (int i = 0; i < currentOrders.size(); i++) {
            if (currentOrders.get(i).getOrderNum() == orderNum) {
                exists = true;

            }
        }

        return exists;
    }

    public String displayOrders(String date) {
        String list = "";

        ArrayList<OrderInterface> givenOrders = orders.get(date);
        if (givenOrders == null) {
            list = "No orders for this day.";
        } else {
            for (int i = 0; i < givenOrders.size(); i++) {
                list += "\n~~~~~~~~~~~~~~\n" + givenOrders.get(i).toString();
            }
        }
        list += "\n";
        return list;
    }
    
    public String displayOrderNums(String date) {
        ArrayList<OrderInterface> tempList = orders.get(date);
        String orders = "";
        
        for (OrderInterface temp : tempList) {
            orders += temp.getOrderNum() + "\n";
        }
        
        return orders;
    }
    
    public String displayDates() {
        String list = "";
        Set<String> dates = orders.keySet();
        
        for (String date : dates) {
            list += date + "\n";        
        }
        
        return list;
    }

    public StateInterface getValidState(String stateName) {
        StateInterface taxState = null;

        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).getStateName().equalsIgnoreCase(stateName)) {
                taxState = states.get(i);
            }
        }
        return taxState;
    }

    public ProductInterface getValidProduct(String material) {
        ProductInterface validMaterial = null;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType().equalsIgnoreCase(material)) {
                validMaterial = products.get(i);
            }
        }
        
        return validMaterial;
    }

    public int getOrderNum() {  //TODO: stream operation to do this??
        int orderCount = 0;

        Set<String> dates = orders.keySet();

        for (String date : dates) {
            ArrayList<OrderInterface> arrayOrders = orders.get(date);

            for (int i = 0; i < arrayOrders.size(); i++) {
                orderCount++;
            }

        }
        return orderCount + 1;
    }
    
    public String getTodaysDate() {
        return todaysDate;
    }

    public OrderInterface getOrder(String date, int orderNum) {
        ArrayList<OrderInterface> currentOrders = orders.get(date);
        OrderInterface tempOrder = null;

        for (int i = 0; i < currentOrders.size(); i++) {
            if (currentOrders.get(i).getOrderNum() == orderNum) {
                tempOrder = currentOrders.get(i);

            }

        }

        return tempOrder;
    }

    public void addOrder(String date, OrderInterface order) {
        Set<String> keys = orders.keySet();
        ArrayList<OrderInterface> tempValue;

        if (keys.contains(date)) {
            tempValue = orders.get(date);
        } else {
            tempValue = new ArrayList<>();
        }
        tempValue.add(order);
        orders.put(date, tempValue);
    }
    
    public void addProduct(ProductInterface product) {
        products.add(product);
    }
    
    public void addState(StateInterface state) {
        states.add(state);
    }

    public void removeOrder(String date, int orderNum) {
        ArrayList<OrderInterface> tempValue = orders.get(date);
        OrderInterface remOrder = null;

        for (int i = 0; i < tempValue.size(); i++) {
            if (tempValue.get(i).getOrderNum() == orderNum) {
                remOrder = tempValue.get(i);
            }
        }

        tempValue.remove(remOrder);
        orders.put(date, tempValue);
    }
    
    public void removeProduct(ProductInterface product) {
        products.remove(product);
    }
    
    public void removeState(StateInterface state) {
        states.remove(state);
    }

    //Method to send information to controller for saving
    public HashMap<String, ArrayList<OrderInterface>> getOrderList() {
        HashMap<String, ArrayList<OrderInterface>> copyList = new HashMap<>();
        copyList.putAll(orders);
        return copyList;
    }
    
    public ArrayList<StateInterface> getStateList() {
        ArrayList<StateInterface> temp = new ArrayList<>();
        
        temp.addAll(states);        
        
        return temp;
    }
    
    public ArrayList<ProductInterface> getProductList() {
        ArrayList<ProductInterface> temp = new ArrayList<>();
        
        temp.addAll(products);        
        
        return temp;
    }

    //Methods to get information from controller for loading
    public void addProducts(ArrayList<ProductInterface> newProductList) {
        products.addAll(newProductList);
    }

    public void addStates(ArrayList<StateInterface> newStateList) {
        states.addAll(newStateList);
    }

    public void addOrders(HashMap<String, ArrayList<OrderInterface>> addedOrders) {
        orders.putAll(addedOrders);
    }
    
    public void setCalendar() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddYYYY");
        todaysDate = formatter.format(today);
    }
}
