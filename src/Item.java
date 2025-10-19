public class Item {
    private String itemName;
    private String itemDescription;
    private int itemID;
    private int itemBasePrice;
    private int itemScalingID;
    public Item(String itemName, String itemDescription, int itemID, int itemBasePrice, int itemScalingID) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemID = itemID;
        this.itemBasePrice = itemBasePrice;
        this.itemScalingID = itemScalingID;
    }
}
