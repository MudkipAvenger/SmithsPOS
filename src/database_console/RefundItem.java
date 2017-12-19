
package database_console;

/**
 *
 * @author A.Smith
 */
public class RefundItem extends Item{
    protected boolean hasBeenRefunded;
    protected boolean hasTaxBeenRefunded;
    protected boolean refundTaxOnly=false;
    protected boolean refundAllActive=false;
    protected String receiptNum;
    protected int quantityBeingRefunded=0;
    
    RefundItem(Database myDB,String receiptNum, String mutID, String upc, String name, double amtPaidBeforeTax, boolean wasTaxed, int category, int rxNumber, String insurance, String filldate, int quantity, boolean isRX, double percentageDisc, boolean isPreCharged,boolean hasBeenRefunded,boolean hasTaxBeenRefunded){
        super(myDB,  mutID,  upc,  name, amtPaidBeforeTax,  amtPaidBeforeTax, wasTaxed, category, rxNumber, insurance, filldate, quantity,  isRX, percentageDisc, isPreCharged);
        this.hasBeenRefunded=hasBeenRefunded;
        this.hasTaxBeenRefunded=hasTaxBeenRefunded;
        this.receiptNum=receiptNum;
    }//end ctor
    
    public boolean isRefundAllActive(){
        return refundAllActive;
    }
    public void setUPC(String upc){
        itemUPC = upc;
    }
    public void setID(String id){
        mutID=id;
    }
    public boolean isRefundTaxOnlyActive(){
        return refundTaxOnly;
    }
    public String getReceiptNum(){
        return receiptNum;
    }
    @Override
    public boolean hasBeenRefunded(){
        return hasBeenRefunded;
    }
    public boolean refundTaxOnly(){
        return refundTaxOnly;
    }
    public boolean refundAllActive(){
        return refundAllActive;
    }
    
    public void setRefundTaxOnly(boolean refundTaxOnly){
        this.refundTaxOnly = refundTaxOnly;
    }
    public void setRefundAllActive(boolean refundAllActive){
        this.refundAllActive=refundAllActive;
    }
    @Override
    public boolean hasTaxBeenRefunded(){
        return hasTaxBeenRefunded;
    }
    @Override
     public double getTotal() {
        double totalOnItem = 0;
        double itemP;
        itemP = itemPrice * quantityBeingRefunded - itemPrice * quantityBeingRefunded * percentageDisc;
        if (isTaxable) {
            totalOnItem = round(itemP + itemP * taxRate);
        } else {
            totalOnItem = round(itemP);
        }
        return totalOnItem;
        //return round(itemPrice);
    }//end getTotal()

    @Override
    double getPriceOfItemsBeforeTax() {
        double total = round(itemPrice * quantityBeingRefunded-itemPrice * quantityBeingRefunded * percentageDisc);
        return total;
    }//end priceOfItemsBeforeTax

    double getPriceOfItemsBeforeTaxWithMaxQtyRefund() {
        double total = round(itemPrice * quantity-itemPrice * quantity * percentageDisc);
        return total;
    }//end priceOfItemsBeforeTax

       
    @Override
    double getTaxTotal() {
        double itemP = itemPrice * quantityBeingRefunded - itemPrice * quantityBeingRefunded * percentageDisc;
        if (isTaxable) {
            double taxTotal = round(itemP * taxRate);
            return taxTotal;
            
        } else {
            return 0.00;
        }
    }//end getTaxTotal
    
    @Override
     public Double getDiscountAmount() {
        double discountAmt = round(quantityBeingRefunded * itemPrice * percentageDisc);
        return discountAmt;
    }
}//end RefundItem
