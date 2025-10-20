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
            itemPrice = linearScaling*(int)(Math.log(purchasedAmount));
        }
        if(scalingID == 2) {
            double logBase2 = Math.log(purchasedAmount)/Math.log(2);
            itemPrice = linearScaling*(int)(logBase2);
        }
        if(scalingID == 3) {
            itemPrice = itemBasePrice*(int)Math.pow(purchasedAmount+1.0,2.0);
        }
    }

    public void tryPurchase(int purchasedAmountToIncrement)
    {
        //use switch and case for this later
        if(scalingID == 0) {
            //2^n - 1 and then subtract 2^z
            //where n is purchasedAmountToIncrement + purchasedAmount
            //and where z is the purchasedAmount
            int totalPurchaseCost = itemBasePrice*(int)(Math.pow(2,purchasedAmount+purchasedAmountToIncrement)-Math.pow(2,purchasedAmount));
            if(gameplay.getStatArray()[itemCurrency]>=totalPurchaseCost) {
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
