package inventoryManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import salableItem.SalableItem;

public class FileSave {
    private List<SalableItem> itemList = new ArrayList<>();
    static final String INVENTORY_ITEMS = "inventory_items.json";

	 /**
     * Persists the current state of the inventory list to a file.
     */
    public void saveToFile() {
        try {
            saveToFile(INVENTORY_ITEMS, itemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * Saves the specified list of items to a file in JSON format.
     *
     * @param fileName The name of the file.
     * @param items    The list of items to save.
     * @throws IOException If an I/O error occurs.
     */
   public static void saveToFile(String fileName, List<SalableItem> items) throws IOException {
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    	 List<SalableItem> existingItems = new ArrayList<>();
    	 File file = new File(fileName);
    	 
    	 if(file.exists()) {
    		 try(Scanner scanner = new Scanner(file)) {
    			 String json = scanner.useDelimiter("\\Z").next();
    	         SalableItem[] itemsArray = objectMapper.readValue(json, SalableItem[].class);
    	         existingItems = new ArrayList<>(Arrays.asList(itemsArray));
    	     }
    	 }

    	    existingItems.clear();
    	    existingItems.addAll(items);

    	String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(existingItems);
    		try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, false))) { 
    	        pw.println(json);
    	    }
    }

    /**
     * Reads and returns a list of items from a JSON file.
     *
     * @param fileName The name of the file.
     * @return         A list of salable items read from the file.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<SalableItem> readFromFile(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String json = scanner.useDelimiter("\\Z").next();
            SalableItem[] items = objectMapper.readValue(json, SalableItem[].class);
            return new ArrayList<>(Arrays.asList(items));
        }
    }
}
