package it.unipd.mtss.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.UserImpl;
import it.unipd.mtss.model.itemType;


public class EShopBillTest {
    
    //////////////////////////////////UNIT///////////////////////////////////////////
    @Test  
    public void getRawTotalTest(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Processor, "i7", 10.50));
            add(new EItem(itemType.Processor, "i5", 5.50));
            add(new EItem(itemType.Motherboard, "nuova", 50.00));
            add(new EItem(itemType.Keyboard, "con le lucine", 100.50));
        }};

       try{
            assertEquals(new EShopBill().getRawTotal(lista), 166.5, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkMoreThanFiveProcessorsDiscountTest(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Processor, "i7", 10.50));
            add(new EItem(itemType.Processor, "i5", 7.50));
            add(new EItem(itemType.Processor, "i2", 2.50));
            add(new EItem(itemType.Processor, "i3", 4.50));
            add(new EItem(itemType.Processor, "i4", 9.50));
            add(new EItem(itemType.Processor, "i4", 9.50));
            add(new EItem(itemType.Motherboard, "nuova", 50.00));
            add(new EItem(itemType.Keyboard, "con le lucine", 100.50));
        }};
        assertEquals(
            193.25, 
            new EShopBill().checkMoreThanFiveProcessorsDiscount(lista, 194.50), 0.01);
    }

    @Test  
    public void checkMoreThanTenMousesDiscountTest(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "rotto", 0.50));
            add(new EItem(itemType.Mouse, "rotto", 0.50));
            add(new EItem(itemType.Mouse, "rotto", 0.50));
            add(new EItem(itemType.Mouse, "economico", 1.50));
            add(new EItem(itemType.Mouse, "base", 5.50));
            add(new EItem(itemType.Mouse, "okay", 10.50));
            add(new EItem(itemType.Mouse, "buono", 15.50));
            add(new EItem(itemType.Mouse, "buonetto", 20.50));
            add(new EItem(itemType.Mouse, "molto buono", 25.50));
            add(new EItem(itemType.Mouse, "buonissimo", 30.50));
        }};
        try{
            assertEquals(new EShopBill().checkMoreThanTenMousesDiscount(lista, 111), 110.50, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    
    @Test
    public void checkSameAmountOfMousesAndKeyboardsDiscountTest(){
        //interpretazione di meno caro: tra mouse e tastiere

        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "medio", 17.50));
            add(new EItem(itemType.Mouse, "gigante", 30.00));
            add(new EItem(itemType.Processor, "i3", 4.50));
            add(new EItem(itemType.Processor, "i4", 9.50));
            add(new EItem(itemType.Motherboard, "nuova", 50.00));
            add(new EItem(itemType.Keyboard, "con le lucine", 100.50));
            add(new EItem(itemType.Keyboard, "senza le lucine", 10.50));
            add(new EItem(itemType.Keyboard, "meccanica", 60.00));
        }};
        assertEquals(
            282.50, 
            new EShopBill()
                .checkSameAmountOfMousesAndKeyboardsDiscount(lista, 293.00), 0.01);
    }
}
