import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class TheSystem {
    //Attributes
    private Map<String, Item> itemCollection;
    private final static int NAME = 0;
    private final static int DESCRIPTION = 1;
    private final static int PRICE = 2;
    private final static int AVAILABLE_QUANTITY = 3;
    
    
    //Start Constructors
    public TheSystem() {
        this.itemCollection = new HashMap<String, Item>();
        
        //if AppSystem then add contents from sample.txt
        if (!"AppSystem".equals(getClass().getSimpleName())) {
            return;
        }
        File file = new File(getClass().getClassLoader().getResource("sample.txt").getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> sampleLines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                sampleLines.add(line);
                System.out.println(line);
            }
            
            itemCollection
                    .putAll(sampleLines.stream().map(sampleLine -> sampleLine.split("  "))
                            .collect(Collectors.toMap(splitLine -> {
                                return splitLine[NAME];
                            }, splitLine -> {
                                Item item = new Item();
                                item.setItemName(splitLine[NAME]);
                                item.setItemDesc(splitLine[DESCRIPTION]);
                                item.setItemPrice(Double.valueOf(splitLine[PRICE]));
                                item.setAvailableQuantity(Integer.valueOf(splitLine[AVAILABLE_QUANTITY]));
                                return item;
                            })));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //End Constructors
    
    //Start Methods
    public boolean checkAvailability(Item item) {
        boolean value = item.getQuantity() >= item.getAvailableQuantity();
        if (value) {
            System.out.format("System is unable to add %s to the card. System only has %d %ss.", item.getItemName(), item.getAvailableQuantity(), item.getItemName());
            return false;
        }
        return true;
    }
    
    public boolean add(Item item) {
        if (item == null) {
            return false;
        }
        Item savedItem = itemCollection.get(item.getItemName());
        if (savedItem != null) {
            if (checkAvailability(item)) {
                savedItem.setQuantity(savedItem.getQuantity() + 1);
                return true;
            }
            return false;
        }
        itemCollection.put(item.getItemName(), item);
        return true;
    }
    
    public Item remove(String itemName) {
        return itemCollection.remove(itemName);
    }
    
    public abstract void display();
    
    public Map<String, Item> getItemCollection() {
        return itemCollection;
    }
    
    public void setItemCollection(HashMap<String, Item> itemCollection) {
        this.itemCollection = itemCollection;
    }
    //End Methods
}
