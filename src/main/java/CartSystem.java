public class CartSystem extends TheSystem {
    CartSystem() {
        super();
    }
    
    @Override
    public void display() {
        StringBuilder displayBuilder = new StringBuilder();
        
        //Header
        displayBuilder
                .append(String.format("%-" + 20 + "s", "Name"))
                .append(String.format("%-" + 20 + "s", "Description"))
                .append(String.format("%-" + 20 + "s", "Price"))
                .append(String.format("%-" + 20 + "s", "Quantity"))
                .append(String.format("%-" + 20 + "s", "Sub Total"))
                .append("\n");
        
        //Actual content
        double subTotal = getItemCollection().values().stream().map(item -> {
            //Sub total
            double itemSubTotal = item.getItemPrice() * item.getQuantity();
            displayBuilder
                    .append(String.format("%-" + 20 + "s", item.getItemName()))
                    .append(String.format("%-" + 20 + "s", item.getItemDesc()))
                    .append(String.format("%-" + 20 + ".2f", item.getItemPrice()))
                    .append(String.format("%-" + 20 + "d", item.getQuantity()))
                    .append(String.format("%-" + 20 + ".2f", itemSubTotal))
                    .append("\n");
            return itemSubTotal;
        }).reduce(Double::sum).orElse(0d);
        displayBuilder.append("\n");
        
        //Total content
        displayBuilder
                .append(String.format("%-" + 20 + "s", "Pre-tax Total"))
                .append(String.format("%-" + 20 + ".2f", subTotal))
                .append("\n");
        displayBuilder
                .append(String.format("%-" + 20 + "s", "Tax"))
                .append(String.format("%-" + 20 + ".2f", subTotal * 0.05))
                .append("\n");
        displayBuilder
                .append(String.format("%-" + 20 + "s", "Total"))
                .append(String.format("%-" + 20 + ".2f", subTotal * 1.05))
                .append("\n");
        
        System.out.println(displayBuilder.toString());
    }
}
