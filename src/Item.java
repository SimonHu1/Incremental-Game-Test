public class Item {
    private String itemName;
    private String itemDescription;
    private int itemCurrency;
    private int itemID;
    private int itemBasePrice;
    private int itemScalingID;
    private int purchasedAmount;
    public Item(String itemName, String itemDescription, int itemCurrency, int itemID, int itemBasePrice, int purchasedAmount, int itemScalingID) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCurrency = itemCurrency;
        this.itemID = itemID;
        this.itemBasePrice = itemBasePrice;
        this.purchasedAmount = purchasedAmount;
        this.itemScalingID = itemScalingID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemCurrency() {
        return itemCurrency;
    }

    public int getItemBasePrice() {
        return itemBasePrice;
    }

    public int getItemScalingID() {
        return itemScalingID;
    }

    public int getPurchasedAmount() {
        return purchasedAmount;
    }
}
