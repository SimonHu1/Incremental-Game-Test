import javax.swing.*;
import java.awt.*;
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
        initializeUpgrade(upgradeID);
    }

    public void initializeUpgrade(int upgradeID)
    {
        //add a ? button for info
        LayoutManager FlowLayout = new FlowLayout();
        setBackground(Color.white);
        setLayout(FlowLayout);
        setOpaque(true);
        add(buyButton);
        add(upgradeName);
        add(upgradeDescription);
        buyButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        upgradeName.setFont(new Font("Monospaced", Font.BOLD, 20));
        upgradeDescription.setFont(new Font("Monospaced", Font.BOLD, 20));
        upgradeName.setEditable(false);
        upgradeDescription.setEditable(false);
        upgradeDescription.setBounds(0,0,100,100);
        upgradeDescription.setForeground(Color.white);
        upgradeDescription.setBackground(Color.BLUE);
        upgradeDescription.setText("Description");
        upgradeDescription.setVisible(true);
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
        int n = purchasedAmountToIncrement + purchasedAmount;
        //use switch and case for this later
        if(scalingID == 0) {
            //(n)(n+1)/2 and then subtract (z)(z+1)/2
            //where n is purchasedAmountToIncrement + purchasedAmount
            //and where z is the purchasedAmount
            int totalPurchaseCost = itemBasePrice*((n*(n+1)/2)-(purchasedAmount*(purchasedAmount+1)/2));
            if(gameplay.getStatArray()[itemCurrency]>=totalPurchaseCost) {
                gameplay.setStatArray(itemCurrency, gameplay.getStatArray()[itemCurrency]-totalPurchaseCost);
                purchasedAmount += purchasedAmountToIncrement;
                //decrement statArray and adjust the purchased amount and new item price accordingly
            }
        }
        if(scalingID == 1) {
            int totalPurchaseCost = itemBasePrice*(int)triangleSummation(purchasedAmount,n+1,10);
            if(gameplay.getStatArray()[itemCurrency]>=totalPurchaseCost) {
                gameplay.setStatArray(itemCurrency, gameplay.getStatArray()[itemCurrency]-totalPurchaseCost);
                purchasedAmount += purchasedAmountToIncrement;
                //decrement statArray and adjust the purchased amount and new item price accordingly
            }
        }
        if(scalingID == 2) {
            int totalPurchaseCost = itemBasePrice*(int)triangleSummation(purchasedAmount,n+1,2);
            if(gameplay.getStatArray()[itemCurrency]>=totalPurchaseCost) {
                gameplay.setStatArray(itemCurrency, gameplay.getStatArray()[itemCurrency]-totalPurchaseCost);
                purchasedAmount += purchasedAmountToIncrement;
                //decrement statArray and adjust the purchased amount and new item price accordingly
            }
        }
        if(scalingID == 3) {
            int totalPurchaseCost = itemBasePrice*((n*(n+1)*(2*n+1)/6)-(purchasedAmount*(purchasedAmount+1)*(2*purchasedAmount+1)/6));
            if(gameplay.getStatArray()[itemCurrency]>=totalPurchaseCost) {
                gameplay.setStatArray(itemCurrency, gameplay.getStatArray()[itemCurrency]-totalPurchaseCost);
                purchasedAmount += purchasedAmountToIncrement;
                //decrement statArray and adjust the purchased amount and new item price accordingly
            }
        }
    }

    public double triangleSummation(int boundOne, int boundTwo, int logBase)
    {
        double funcOneB1 = (boundOne + 1.0)*Math.log(boundOne+10.0)/Math.log(logBase);
        double funcOneB2 = (boundTwo + 1.0)*Math.log(boundTwo+10.0)/Math.log(logBase);
        double funcTwoB1 = 0.5*(boundOne*boundOne + 2*boundOne - 80.0)*Math.log(boundOne+10.0) - 0.25*(boundOne*boundOne) + 4.0*boundOne;
        double funcTwoB2 = 0.5*(boundTwo*boundTwo + 2*boundTwo - 80.0)*Math.log(boundTwo+10.0) - 0.25*(boundTwo*boundTwo) + 4.0*boundTwo;
        double integralPart = (funcTwoB2 - funcTwoB1)/Math.log(logBase);
        double trapezoid = 0.5*(funcOneB1+funcOneB2);
        return integralPart + trapezoid;
    }
}
