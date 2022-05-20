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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class EShopBill implements Bill{
    
    List<EItem> itemsOrdered;
    User user;
    Date date;

    public EShopBill(){
        
    }

    public EShopBill(List<EItem> itemsOrdered, User user, Date date){
        this.itemsOrdered = itemsOrdered;
        this.user = user;
        this.date = date;
    }
  
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

    public double checkIfTotalIsLessThanTen(double total){           //requisito 7
        if(total > 0 && total < 10){
            return total + 2;
        }
        return total;
    }
    
    public static void makeFreeOrder(List<EShopBill> bills) {              //requisito 8

        List<EShopBill> validOrders = new ArrayList<EShopBill>();
        Calendar calendar = Calendar.getInstance();
            for (EShopBill bill: bills) {
                calendar.setTime(bill.date);
                if (bill.user.getAge() <= 18 
                && calendar.get(Calendar.HOUR_OF_DAY) >= 18 
                && calendar.get(Calendar.HOUR_OF_DAY) < 19) {
                    validOrders.add(bill);
                }
            }
        
        List<User> alreadyGifted = new ArrayList<User>();
        int freeOrders = 0;
        while (freeOrders < 10 && !validOrders.isEmpty()) {
            int rand = new Random().nextInt(validOrders.size());
            User luckyPerson = validOrders.get(rand).user;
            //validità (ordini di user diversi)
            if (!alreadyGifted.contains(luckyPerson)) {
                freeOrders++;
                alreadyGifted.add(luckyPerson);
            }
            bills.remove(validOrders.get(rand));
            validOrders.remove(rand);
        }
    }

    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException{
        if(itemsOrdered != null){
            double total = getRawTotal(itemsOrdered);                                    //R1
            total = checkMoreThanFiveProcessorsDiscount(itemsOrdered, total);            //R2
            total = checkMoreThanTenMousesDiscount(itemsOrdered, total);                 //R3
            total = checkSameAmountOfMousesAndKeyboardsDiscount(itemsOrdered, total);    //R4
            total = checkMoreThanAThousandSpentDiscount(total);                          //R5
            total = checkIfMoreThanThirtyItemsOrdered(itemsOrdered, total);              //R6
            total = checkIfTotalIsLessThanTen(total);                                    //R7    
            return total;                                   
        }           
        else{
            throw(new BillException(1, "Null list given"));
        }                
    }
}

