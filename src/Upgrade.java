import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Upgrade extends JLabel {
    private Gameplay gameplay;
    private Item thisItem;
    private JButton buyButton = new JButton("BUY");
    private JTextField upgradeName = new JTextField();
    private JTextField upgradeDescription = new JTextField();
    private JTextField upgradePrice = new JTextField();
    private int itemCurrency;
    private int itemPrice;
    private int itemBasePrice;
    private int purchasedAmount;
    private int scalingID;
    public Upgrade(Gameplay gameplay, int upgradeID) {
        this.gameplay = gameplay;
        thisItem = gameplay.getItemList().get(upgradeID);
        upgradeName.setText(thisItem.getItemName());
        upgradeDescription.setText(thisItem.getItemDescription());
        itemCurrency = thisItem.getItemCurrency();
        itemBasePrice = thisItem.getItemBasePrice();
        purchasedAmount = thisItem.getPurchasedAmount();
        scalingID = thisItem.getItemScalingID();
        upgradePrice.setText("Placeholder");
    }

    public void calculateItemPrice() {
        //use switch and case for this later
        int linearScaling = itemBasePrice*(purchasedAmount+1);
        if(scalingID == 0) {
            itemPrice = linearScaling;
        }
        if(scalingID == 1) {
            itemPrice = (int)(linearScaling*(Math.log(purchasedAmount+10)));
        }
        if(scalingID == 2) {
            double logBase2 = Math.log(purchasedAmount+1)/Math.log(2);
            itemPrice = (int)(linearScaling*(logBase2));
        }
        if(scalingID == 3) {
            itemPrice = (int)(itemBasePrice*Math.pow(purchasedAmount+1.0,2.0));
        }
    }

    public void tryPurchase(int purchasedAmountToIncrement)
    {
        //use switch and case for this later
        if(scalingID == 0) {
            //(n)(n+1)/2 and then subtract (z)(z+1)/2
            //where n is purchasedAmountToIncrement + purchasedAmount
            //and where z is the purchasedAmount
            int n = purchasedAmountToIncrement + purchasedAmount;
            int totalPurchaseCost = itemBasePrice*((n*(n+1)/2)-(purchasedAmount*(purchasedAmount+1)/2));
            if(gameplay.getStatArray()[itemCurrency]>=totalPurchaseCost) {
                gameplay.setStatArray(itemCurrency, gameplay.getStatArray()[itemCurrency]-totalPurchaseCost);
                purchasedAmount += purchasedAmountToIncrement;
                //decrement statArray and adjust the purchased amount and new item price accordingly
            }
        }
        if(scalingID == 1) {

        }
        if(scalingID == 2) {

        }
        if(scalingID == 3) {

        }
    }
}
