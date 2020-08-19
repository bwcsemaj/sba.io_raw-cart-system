package com.github.perscholas;


public class AppSystem extends TheSystem {
    AppSystem() {
    }
    
    @Override
    public void display() {
        StringBuilder displayBuilder = new StringBuilder();
        
        //Header
        displayBuilder
                .append(String.format("%-" + 20 + "s", "Name"))
                .append(String.format("%-" + 20 + "s", "Description"))
                .append(String.format("%-" + 20 + "s", "Price"))
                .append(String.format("%-" + 20 + "s", "Available Quantity"))
                .append("\n");
        
        //Actual content
        getItemCollection().values().stream().forEach(item -> {
            displayBuilder
                    .append(String.format("%-" + 20 + "s", item.getItemName()))
                    .append(String.format("%-" + 20 + "s", item.getItemDesc()))
                    .append(String.format("%-" + 20 + "d", item.getItemPrice()))
                    .append(String.format("%-" + 20 + "d", item.getAvailableQuantity()))
                    .append("\n");
        });
        System.out.println(displayBuilder.toString());
    }
    
    @Override
    public boolean add(Item item) {
        if (item == null) {
            return false;
        }
        Item savedItem = getItemCollection().get(item.getItemName());
        if (savedItem != null) {
            System.out.format("%s is already in the App System", item.getItemName());
            return false;
        }
        getItemCollection().put(item.getItemName(), item);
        return true;
    }
    
    public Item reduceAvailableQuantity(String itemName) {
        Item item = getItemCollection().get(itemName);
        if(item != null){
            item.setAvailableQuantity(item.getAvailableQuantity() - 1);
        }
        return item;
    }
}
