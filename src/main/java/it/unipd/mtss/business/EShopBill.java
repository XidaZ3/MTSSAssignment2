////////////////////////////////////////////////////////////////////
// XIDA CHEN 1217780
// DANILO STOJKOVIC 1222399
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.itemType;
import it.unipd.mtss.model.User;
import it.unipd.mtss.business.EShopBill;

import java.util.List;


public class EShopBill implements Bill{
  
    public double getRawTotal(List<EItem> itemsOrdered){     //requisito 1
        double total = 0.0;
        for(EItem item : itemsOrdered){
            total += item.getPrice();                               
        }
        return total;
    }
    
    public double checkMoreThanFiveProcessorsDiscount(List<EItem> itemsOrdered, double total){      //requisito 2
        int counter = 0;
        double cheapest = Double.POSITIVE_INFINITY;
        for(EItem item : itemsOrdered){
            if(item.getType() == itemType.Processor){
                counter++;
                if(item.getPrice() < cheapest){
                    cheapest = item.getPrice();
                }
            }                             
        }
        if(counter >= 5){                               
            total -= cheapest / 2;
        }
        return total;
    }

    public double checkMoreThanTenMousesDiscount(List<EItem> itemsOrdered, double total){           //requisito 3
        int counter = 0;
        double cheapest = Double.POSITIVE_INFINITY;
        for(EItem item : itemsOrdered){
            if(item.getType() == itemType.Mouse){
                counter++;
                if(item.getPrice() < cheapest){
                    cheapest = item.getPrice();
                }
            }                             
        }
        if(counter >= 10){                               
            total -= cheapest;
        }
        return total;
    }

    public double checkSameAmountOfMousesAndKeyboardsDiscount(List<EItem> itemsOrdered, double total){  //requisito 4
        int counterMouse = 0, counterKeyboard = 0;
        double cheapestMouse = Double.POSITIVE_INFINITY, cheapestKeyboard = Double.POSITIVE_INFINITY;
        for(EItem item : itemsOrdered){
            if(item.getType() == itemType.Mouse){
                counterMouse++;
                if(item.getPrice() < cheapestMouse){
                    cheapestMouse = item.getPrice();
                }
            }   
            else if(item.getType() == itemType.Keyboard){
                counterKeyboard++;
                if(item.getPrice() < cheapestKeyboard){
                    cheapestKeyboard = item.getPrice();
                }
            }                            
        }
        if(counterMouse == counterKeyboard && counterMouse != 0){                               
            total -= Math.min(cheapestMouse, cheapestKeyboard);
        }
        return total;
    }

    public double checkMoreThanAThousandSpentDiscount(double total){           //requisito 5
        if(total > 1000){
            return total * 0.9;
        }
        return total;
    }
    
    public double checkIfMoreThanThirtyItemsOrdered(List<EItem> itemsOrdered, double total) throws BillException{   //requisito 6
        if(itemsOrdered.size() > 30){                                           
            throw new BillException(0, "troppi ordini");
        }
        return total;
    }

    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException{

        double total = getRawTotal(itemsOrdered);                                    //R1
        total = checkMoreThanFiveProcessorsDiscount(itemsOrdered, total);            //R2
        total = checkMoreThanTenMousesDiscount(itemsOrdered, total);                 //R3
        total = checkSameAmountOfMousesAndKeyboardsDiscount(itemsOrdered, total);    //R4
        total = checkMoreThanAThousandSpentDiscount(total);                          //R5
        total = checkIfMoreThanThirtyItemsOrdered(itemsOrdered, total);              //R6
        return total;
    }
}

