package DataTransferObject;

import Operations.*;
import Storage.Storage;
import UserInterface.UserInterface;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emma/James
 */
public class OrderController {

    private UserInterface io = new UserInterface();
    private Storage sto = new Storage();
    private OrderList list = new OrderList();
    private int config;

    public String getDateFromList() {
        String choice;
        String date;
        boolean exit = false;

        date = io.readString("Enter date (MMDDYYY): ");

        while (!list.dateExist(date) && !date.equals("") && !exit) {
            choice = io.readString("Date not found. "
                    + "\nShow list of available dates? \nY or N : ");
            if (choice.equals("")) {
                exit = true;
            }
            while (!choice.equalsIgnoreCase("y")
                    && !choice.equalsIgnoreCase("n") && !exit) {
                choice = io.readString("Please enter Y or N. "
                        + "\nHit enter to exit: ");
                if (choice.equals("")) {
                    exit = true;
                }
            }
            if (choice.equalsIgnoreCase("y")) {
                io.write(list.displayDates());
            }
            date = io.readString("Enter new date: ");
        }

        return date;
    }

    public String getDateNew() {
        String date;
        String choice;

        choice = io.readString("Backdated order? Y or N: ");
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            choice = io.readString("Please enter Y or N. ");
        }
        if (choice.equalsIgnoreCase("y")) {
            int month, day, year, currentYear;
            String todaysDate;

            todaysDate = list.getTodaysDate();
            currentYear = Integer.parseInt(todaysDate.substring(4));

            // company was established in 1990. Orders cannot be backdated 
            // before the establishment of the company.
            year = io.readInteger("Enter year (YYYY): ", 1990, currentYear,
                    "Please choose a year between \n1990 and " + currentYear);
            month = io.readInteger("Enter month (MM): ", 1, 12,
                    "There are only 12 months in \nthe Gregorian calendar. "
                    + "\nPlease input month accordingly.");
            if (month == 9 || month == 4 || month == 6 || month == 11) {
                day = io.readInteger("Enter day (DD): ", 1, 30,
                        "This month has only 30 days.");
            } else if (month == 2 && year % 4 == 0) {
                day = io.readInteger("Enter day (DD): ", 1, 29,
                        "This month has only 29 days.");
            } else if (month == 2) {
                day = io.readInteger("Enter day (DD): ", 1, 28,
                        "This month has only 28 days.");
            } else {
                day = io.readInteger("Enter date (DD): ", 1, 31,
                        "This month has only 31 days.");
            }

            if (month < 10) {
                date = "0" + month;
            } else {
                date = "" + month;
            }
            if (day < 10) {
                date += "0" + day;
            } else {
                date += day;
            }
            date += year;
        } else {
            date = list.getTodaysDate();
        }

        return date;

    }

    public int getOrderNum(String date) {
        int orderNum = -1;
        String num, showOrder;
        boolean failed, exit;

        do {
            num = io.readString("Enter order number: ");
            if (!num.equals("")) {

                do {
                    failed = false;

                    try {
                        orderNum = Integer.parseInt(num);

                    } catch (NumberFormatException e) {
                        io.writeln("Please enter a number.");
                        num = io.readString("Enter order number: ");
                        failed = true;
                    }

                } while (failed);

                if (!list.orderExist(date, orderNum)) {
                    showOrder = io.readString("Order not found. "
                            + "\nShow list of available orders? \nY or N : ");
                    do {
                        exit = true;

                        if (showOrder.equalsIgnoreCase("y")) {
                            io.write(list.displayOrderNums(date));
                            exit = false;
                        } else if (showOrder.equalsIgnoreCase("n")) {
                            exit = false;
                        } else {
                            showOrder = io.readString("Please enter Y or N. "
                                    + "\nHit enter to exit: ");
                            if (showOrder.equals("")) {
                                exit = false;
                            }
                        }
                    } while (exit);
                }
            }

        } while (!list.orderExist(date, orderNum) && !num.equals(""));

        if (num.equals("")) {
            orderNum = -1;
        }

        return orderNum;
    }
    
    // list product types y/n
    public ProductInterface getValidProduct() {
        ProductInterface product = null;
        String material;
        double costPerSqFt;
        double laborPerSqFt;
        String quit; 
        
        material = io.readString("Enter product type to edit: ");
        if (!material.equals("")) {
            product = list.getValidProduct(material);
            while (product == null) {
                io.writeln("That is not a valid material.");
                material = io.readString("Enter the flooring material: ");
                product = list.getValidProduct(material);
            }
        }
        product = list.getValidProduct(material);
        
        return product;
    }
    
    // needs implementation
    public StateInterface getValidState() {
        return null;
    }

    public void displayOrder() {
        String date;

        io.writeln("\nDisplay Order");
        date = getDateFromList();
        if (!date.equals("")) {
            io.writeln(list.displayOrders(date));
        }

    }

    public void addOrder() {
        String date;
        OrderInterface order;

        io.writeln("\nAdd Order");

        date = getDateNew();
        io.writeln("Order date: "
                + date.substring(0, 2) + "-"
                + date.substring(2, 4) + "-"
                + date.substring(4));
        order = makeOrder();

        list.addOrder(date, order);
        io.writeln("");

    }

    public OrderInterface makeOrder() {
        Order order;
        int orderNum;
        String custLastName, custFirstName, state, material;
        double area;
        StateInterface tax;
        ProductInterface product;

        custLastName = io.readString("Enter customer last name: ");
        custFirstName = io.readString("Enter customer first name: ");
        area = io.readDouble("Enter the area of project: ");
        state = io.readString("Enter customer state: ");

        tax = list.getValidState(state);
        while (tax == null) {
            io.writeln("There is not tax information for that state.");
            state = io.readString("Enter customer state: ");
            tax = list.getValidState(state);
        }

        material = io.readString("Enter the flooring material: ");
        product = list.getValidProduct(material);
        while (product == null) {
            io.writeln("That is not a valid material.");
            material = io.readString("Enter the flooring material: ");
            product = list.getValidProduct(material);

        }

        orderNum = list.getOrderNum();
        order = new Order(orderNum, custLastName, custFirstName, area, tax, product);
        return order;

    }

    public void editOrder() {
        String date;
        int orderNum;

        io.writeln("\nEdit Order");
        date = getDateFromList();
        if (!date.equals("")) {
            orderNum = getOrderNum(date);

            if (orderNum != -1) {
                makeChanges(date, orderNum);
            }
        }

    }

    public void makeChanges(String date, int orderNum) {
        OrderInterface editOrder;
        String custLastName;
        String custFirstName;
        String strArea;
        double area;
        String state;
        StateInterface tax = null;
        String material;
        ProductInterface product = null;

        editOrder = list.getOrder(date, orderNum);
        io.writeln("Enter new information. Hit enter to make no changes.");
        custLastName = io.readString("\nEnter customer last name (" + editOrder.getCustLastName() + ") : ");
        custFirstName = io.readString("Enter customer first name (" + editOrder.getCustFirstName() + ") : ");
        strArea = io.readString("Enter area (" + editOrder.getArea() + ") : ");
        state = io.readString("Enter state (" + editOrder.getState().getStateName() + ") : ");
        if (!state.equals("")) {
            tax = list.getValidState(state);
            while (tax == null) {
                io.writeln("There is not tax information for that state.");
                state = io.readString("Enter customer state: ");
                tax = list.getValidState(state);
            }
        }
        
        product = getValidProduct();

        if (!custLastName.equals("")) {
            editOrder.setCustLastName(custLastName);
        }
        if (!custFirstName.equals("")) {
            editOrder.setCustFirstName(custFirstName);
        }
        if (!strArea.equals("")) {
            area = Double.parseDouble(strArea);
            editOrder.setArea(area);
        }
        if (tax != null) {
            editOrder.setState(tax);
        }
        if (product != null) {
            editOrder.setProduct(product);
        }
        io.writeln("");

    }

    public void removeOrder() {
        String date, choice;
        int orderNum;

        io.writeln("\nRemove Order");
        date = getDateFromList();

        orderNum = getOrderNum(date);

        if (orderNum != -1) {

            io.write(list.getOrder(date, orderNum).toString());
            do {
                choice = io.readString("\nReally delete this order? Y or N : ");

            } while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")));

            if (choice.equalsIgnoreCase("y")) {
                list.removeOrder(date, orderNum);
            }
        }

    }

    // calls all load methods
    public void load() {
        loadProduct();
        loadState();
        loadOrders();
        loadTodaysDate();
        loadConfigSetting();
    }

    public void loadProduct() {
        ArrayList<ProductInterface> productList = new ArrayList<>();
        ArrayList<String[]> products = sto.readFromFile("Data/Products.txt");
        for (int i = 0; i < products.size(); i++) {
            String[] item = products.get(i);

            for (int j = 0; j < item.length; j++) {
                ProductInterface product = new Product(item[0],
                        Double.parseDouble(item[1]),
                        Double.parseDouble(item[2]));
                productList.add(product);
            }
        }
        list.addProducts(productList);
    }

    public void loadState() {
        ArrayList<StateInterface> stateList = new ArrayList<>();
        ArrayList<String[]> states = sto.readFromFile("Data/States.txt");
        for (int i = 0; i < states.size(); i++) {
            String[] item = states.get(i);

            for (int j = 0; j < item.length; j++) {
                StateInterface state = new State(item[0], Double.parseDouble(item[1]));
                stateList.add(state);

            }
        }
        list.addStates(stateList);
    }

    public void loadOrders() {
        File folder = new File("Data/Orders/");
        File[] listOfOrders = folder.listFiles();
        HashMap<String, ArrayList<OrderInterface>> orders = new HashMap<>();

        for (File file : listOfOrders) {
            if (file.isFile()) {
                String filename;
                ArrayList<OrderInterface> orderByDate = new ArrayList<>();
                ArrayList<String[]> orderList = sto.readFromFile("Data/Orders/" + file.getName());

                for (int i = 0; i < orderList.size(); i++) {
                    String[] item = orderList.get(i);
                    Order order = new Order(Integer.parseInt(item[0]),
                            item[1],
                            item[2],
                            Double.parseDouble(item[6]),
                            new State(item[3],
                                    Double.parseDouble(item[4])),
                            new Product(item[5],
                                    Double.parseDouble(item[7]),
                                    Double.parseDouble(item[8])));
                    orderByDate.add(order);
                }

                filename = file.getName();
                filename = filename.substring(7, filename.length() - 4);
                orders.put(filename, orderByDate);
            }

        }
        list.addOrders(orders);
    }

    public void loadTodaysDate() {
        list.setCalendar();
    }

    public void loadConfigSetting() {
        int configSetting;
        ArrayList<String[]> num = sto.readFromFile("Data/Config.txt");

        configSetting = Integer.parseInt(num.get(1)[0]);

        this.config = configSetting;

    }
    
    // save methods
    public void saveOrders() {
        HashMap<String, ArrayList<OrderInterface>> orderList = list.getOrderList();
        HashMap<String, ArrayList<String[]>> elements = new HashMap<>();
        Set<String> orderKeys = orderList.keySet();

        io.writeln("Saving");

        try {
            for (String date : orderKeys) {
                String filename;
                ArrayList<OrderInterface> orders = orderList.get(date);
                ArrayList<String[]> writeOrder = new ArrayList<>();

                filename = "Data/Orders/Orders_" + date + ".txt";
                for (int i = 0; i < orders.size(); i++) {
                    OrderInterface tempOrder;
                    String[] orderElements = new String[13];

                    tempOrder = orders.get(i);
                    orderElements[0] = "" + tempOrder.getOrderNum();
                    orderElements[1] = tempOrder.getCustLastName();
                    orderElements[2] = tempOrder.getCustFirstName();
                    orderElements[3] = tempOrder.getState().getStateName();
                    orderElements[4] = "" + tempOrder.getState().getTaxRate();
                    orderElements[5] = tempOrder.getProduct().getType();
                    orderElements[6] = "" + tempOrder.getArea();
                    orderElements[7] = "" + tempOrder.getProduct().getCostPerSqFt();
                    orderElements[8] = "" + tempOrder.getProduct().getLaborPerSqFt();
                    orderElements[9] = "" + tempOrder.getMaterialCostTotal();
                    orderElements[10] = "" + tempOrder.getLaborCostTotal();
                    orderElements[11] = "" + tempOrder.getTaxTotal();
                    orderElements[12] = "" + tempOrder.getTaxTotal();

                    writeOrder.add(orderElements);
                }

                elements.put(filename, writeOrder);
            }

            sto.writeToFile(elements);

        } catch (IOException ex) {
            io.write("Save orders failed.");
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveConfig() {
        ArrayList<String[]> save = new ArrayList<>();
        String[] info = new String[1];
        String[] configSave = new String[1];
        String filename;

        io.writeln("Saving config file");

        try {
            info[0] = "Configuration setting. 0 for test mode. 1 for production mode. 2 for admin mode.";
            configSave[0] = "" + this.config;
            save.add(info);
            save.add(configSave);

            filename = "Data/Config.txt";

            sto.writeToFile(filename, save);
            
        } catch (IOException ex) {
            io.writeln("Save config failed.");
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveProducts() {
        ArrayList<ProductInterface> productList = list.getProductList();
        ArrayList<String[]> products = new ArrayList<>();
        String filename;
        
        try {
            for (int i = 0; i < productList.size(); i++) {
                ProductInterface temp;
                String[] item = new String[3];

                temp = productList.get(i);
                item[0] = temp.getType();
                item[1] = "" + temp.getCostPerSqFt();
                item[2] = "" + temp.getLaborPerSqFt();

                products.add(item);            
            }
            filename = "Data/Products.txt";

            sto.writeToFile(filename, products);
            
        } catch (IOException ex) {
            
            io.writeln("Save products failed.");
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void saveStates() {
        ArrayList<StateInterface> stateList = list.getStateList();
        ArrayList<String[]> states = new ArrayList<>();
        String filename;
        
        try {
            for (int i = 0; i < stateList.size(); i++) {
                StateInterface temp;
                String[] item = new String[2];

                temp = stateList.get(i);
                item[0] = temp.getStateName();
                item[1] = "" + temp.getTaxRate();

                states.add(item);            
            }
            filename = "Data/States.txt";

            sto.writeToFile(filename, states);
            
        } catch (IOException ex) {
            
            io.writeln("Save states failed.");
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    // Admin mode methods
    public void viewProducts() {
        io.writeln("Not implemented yet");
    }
    
    public void addProduct() {
        String type;
        double costPerSqFt;
        double laborPerSqFt;
        ProductInterface product;
        String quit;
        
        io.writeln("Adding a new Product");
        
        type = io.readString("Enter new material type: ");
        costPerSqFt = io.readDouble("Enter cost per sq ft: $");
        laborPerSqFt = io.readDouble("Enter labor cost per sq ft: $");
        product = new Product(type, costPerSqFt, laborPerSqFt);
        quit = io.readString("\nProduct created: "
                + "\n" + product.toString() 
                + "\nSave this product? "
                + "\nY to save N to quit : ");
        if (quit.equalsIgnoreCase("y")) {
            list.addProduct(product);
        }
        
    }
    
    public void editProduct() {
        ProductInterface newProduct;
        ProductInterface oldProduct;
        String material;
        boolean failed;
        String cost;
        double costPerSqFt = 0;
        String labor;
        double laborPerSqFt = 0;
        String quit; 
        
        oldProduct = getValidProduct();
        io.writeln("Enter new information. Hit enter to make no changes.");
        
        material = io.readString("Enter material type (" 
                + oldProduct.getType() + "): ");
        if (material.equals("")) {
            material = oldProduct.getType();
        }
        
        cost = io.readString("Enter cost per sq ft ($" 
                + oldProduct.getCostPerSqFt() + ") : $");
        if (!cost.equals("")) {
            do {
                failed = false;
                try {
                    costPerSqFt = Double.parseDouble(cost);
                } catch (NumberFormatException e) {
                    io.writeln("Please enter a number.");
                    cost = io.readString("Enter cost per sq ft ($" 
                            + oldProduct.getCostPerSqFt() + ") : $");
                    failed = true;
                }
            } while (failed);
        } else {
            costPerSqFt = oldProduct.getCostPerSqFt();
        }
        
        labor = io.readString("Enter labor per sq ft ($" 
                + oldProduct.getLaborPerSqFt() + ") : $");
        if (!labor.equals("")) {
            do {
                failed = false;
                try {
                    laborPerSqFt = Double.parseDouble(labor);
                } catch (NumberFormatException e) {
                    io.writeln("Please enter a number.");
                    labor = io.readString("Enter labor per sq ft ($" 
                            + oldProduct.getLaborPerSqFt() + ") : $");
                    failed = true;
                }
            } while (failed);
        } else {
            laborPerSqFt = oldProduct.getLaborPerSqFt();
        }
      
        newProduct = new Product(material, costPerSqFt, laborPerSqFt);
        
        quit = io.readString("Edited Product: "
                + "\n" + newProduct.toString()
                + "\nSave this product? Y to save, N to quit : ");
         if (quit.equalsIgnoreCase("y")) {
            list.addProduct(newProduct);
            list.removeProduct(oldProduct);
        }
        
    }
    
    public void viewStates() {
        io.writeln("Not implemented yet");
    }
    
    public void addState() {
        String stateName;
        double taxRate;
        StateInterface state;
        String quit;
        
        io.writeln("Adding a new State");
        
        stateName = io.readString("Enter new state abbreviation: ");
        taxRate = io.readDouble("Enter tax rate: %");
        state = new State(stateName, taxRate);
        quit = io.readString("State created: "
                + "\n" + state.toString() 
                + "\nSave this product? Y to save, N to quit : ");
        if (quit.equalsIgnoreCase("y")) {
            list.addState(state);
        }
       
    }
    
    public void editState() {
        StateInterface oldState;
        StateInterface newState;
        
        io.writeln("Edit State not implemented yet");
    }
    
    public void adminSave() {
        saveConfig();
        saveProducts();
        saveStates();
    }

    // main run method
    public void run() {
        int choice;
        boolean quit = false;
        String save;

        load();
        
        // if 0 test mode. No chance for saving.
        if (config == 0) {
            while (!quit) {
                choice = displayTestMenu();
                if (choice == 1) {
                    displayOrder();
                } else if (choice == 2) {
                    addOrder();
                } else if (choice == 3) {
                    editOrder();
                } else if (choice == 4) {
                    removeOrder();
                } else if (choice == 5) {
                    quit = true;
                }
            }
        
        // if 1 production mode. Can save
        } else if (config == 1) {
            while (!quit) {
                choice = displayProdMenu();
                if (choice == 1) {
                    displayOrder();
                } else if (choice == 2) {
                    addOrder();
                } else if (choice == 3) {
                    editOrder();
                } else if (choice == 4) {
                    removeOrder();
                } else if (choice == 5) {
                    saveOrders();
                } else if (choice == 6) {
                    quit = true;
                }
            }
            saveOrders();
        
        // if 2 admin mode. All edits to product and state items will be saved
        } else if (config == 2) {
            while (!quit) {
                choice = displayAdminMenu();
                if (choice == 1) {
                    viewProducts();
                } else if (choice == 2) {
                    addProduct();
                } else if (choice == 3) {
                    editProduct();
                } else if (choice == 4) {
                    viewStates();
                } else if (choice == 5) {
                    addState();
                } else if (choice == 6) {
                    editState();
                } else if (choice == 7) {
                    adminSave();
                } else if (choice == 8) {
                    quit = true;
                }
                
                adminSave();
            }
        
        // if anything else config file will be fixed    
        } else {
            io.writeln("Configuration file not set up properly");
            choice = io.readInteger("Enter 1 for test mode "
                    + "\nEnter 2 for production mode "
                    + "\nEnter 3 for admin mode to change the "
                    + "\n        list of products or "
                    + "\n        state tax information", 0, 2);
            do {
                save = io.readString("Choice is" + choice
                        + "\nProceede? Y to save or N to exit program:");
            } while (!(save.equalsIgnoreCase("y") || save.equalsIgnoreCase("n")));

            if (save.equalsIgnoreCase("y")) {
                config = choice;
                saveConfig();
            }

        }
    }

    public int displayProdMenu() {

        io.writeln("****************************");
        io.writeln("*  Flooring Program        *");
        io.writeln("*  1) Display Orders       *");
        io.writeln("*  2) Add an Order         *");
        io.writeln("*  3) Edit an Order        *");
        io.writeln("*  4) Remove an Order      *");
        io.writeln("*  5) Save Current Work    *");
        io.writeln("*  6) Quit                 *");
        io.writeln("****************************");
        return io.readInteger("Enter choice: ", 1, 6);

    }

    public int displayTestMenu() {

        io.writeln("****************************");
        io.writeln("*  ~~~~!!TEST MODE!!~~~    *");
        io.writeln("*  Flooring Program        *");
        io.writeln("*  1) Display Orders       *");
        io.writeln("*  2) Add an Order         *");
        io.writeln("*  3) Edit an Order        *");
        io.writeln("*  4) Remove an Order      *");
        io.writeln("*  5) Quit                 *");
        io.writeln("****************************");
        return io.readInteger("Enter choice: ", 1, 5);

    }
    
    public int displayAdminMenu() {
        
        io.writeln("*************************************");
        io.writeln("*  ~~~!!???ADMIN MODE??!!~~~        *");
        io.writeln("*  Flooring Program                 *");
        io.writeln("*  1) List Products in Inventory    *");
        io.writeln("*  2) Add Product to Inventory      *");
        io.writeln("*  3) Edit Existing Product         *");
        io.writeln("*  4) List State Tax Information    *");
        io.writeln("*  5) Add State Tax Information     *");
        io.writeln("*  6) Edit Existing State Tax Info  *");
        io.writeln("*  7) Save Changes                  *");
        io.writeln("*  8) Quit                          *");
        io.writeln("*************************************");
        return io.readInteger("Enter choice: ", 1, 8);
        
    }

}
