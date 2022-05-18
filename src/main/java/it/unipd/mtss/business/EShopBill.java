////////////////////////////////////////////////////////////////////
// XIDA CHEN 1217780
// DANILO STOJKOVIC 1222399
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
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


    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException{

        double total = getRawTotal(itemsOrdered);                                    //R1
        return total;
    }


}

