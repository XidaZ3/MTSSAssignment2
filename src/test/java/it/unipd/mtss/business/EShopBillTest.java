package it.unipd.mtss.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.UserImpl;
import it.unipd.mtss.model.itemType;


public class EShopBillTest {
    
    UserImpl xida;
    //////////////////////////////////UNIT///////////////////////////////////////////
    @Before
    public void prepareUserImpl(){
        xida= new UserImpl(0, "xida", 21);
    }

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

    @Test  
    public void checkMoreThanAThousandSpentDiscountTest(){
        try{
            assertEquals(new EShopBill().checkMoreThanAThousandSpentDiscount(2301.5), 2071.35, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    
    @Test
    public void checkMoreThanAThousandSpentDiscountNegativeTest(){
        try{
            assertEquals(new EShopBill().checkMoreThanAThousandSpentDiscount(2), 2, 0.0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void checkIfMoreThanThirtyItemsOrderedTest(){
        
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
        }};
        try{
            assertEquals(
                325.50, 
                new EShopBill()
                    .checkIfMoreThanThirtyItemsOrdered(lista, 325.50), 0.01);
        }
        catch(BillException e){
            assertEquals(e.id, 0);
        }
    }

    @Test
    public void checkIfMoreThanThirtyItemsOrderedLessTest(){
        
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
        }};
        try{
            assertEquals(
                315.00, 
                new EShopBill()
                    .checkIfMoreThanThirtyItemsOrdered(lista, 315.00), 0.01);
        }
        catch(BillException e){
            assertEquals(e.id, 0);
        }
    }

    @Test  
    public void checkIfTotalIsLessThanTenTest(){
        try{
            assertEquals(new EShopBill().checkIfTotalIsLessThanTen(7), 9.00, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkIfTotalIsLessThanTenTestNegative(){
        try{
            assertEquals(new EShopBill().checkIfTotalIsLessThanTen(10.1), 10.1, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    
    @Test
    public void makeGiftsWhenBoughtBetween18and19hour()

    {
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

        List<EShopBill> ordineDanilo = new ArrayList<EShopBill>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 1, 18, 30, 59);
        ordineDanilo.add(new EShopBill(lista, new UserImpl(0, "Danilo", 21), calendar.getTime()));
        List<EShopBill> ordiniPEGI12 = new LinkedList<>();
        ordiniPEGI12.add(ordineDanilo.get(0));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(1, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(2, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(3, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(4, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(5, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(6, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(7, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(8, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(9, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(10, "Danilo", 10), calendar.getTime()));
        EShopBill.makeFreeOrder(ordiniPEGI12);
        assertEquals(ordineDanilo.get(0), ordiniPEGI12.get(0));
    }

    @Test
    public void getOrderPriceWithNoDiscount(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "medio", 20.50));
            add(new EItem(itemType.Mouse, "gigante", 100.50));
            add(new EItem(itemType.Processor, "m2", 0.50));
            add(new EItem(itemType.Keyboard, "meccanica", 20.00));
            add(new EItem(itemType.Motherboard, "non so nomi di schede madri", 50.50));
            add(new EItem(itemType.Motherboard, "basta non ne metto altre", 20.00));
            add(new EItem(itemType.Mouse, "un altro mouse", 30.00));
            add(new EItem(itemType.Keyboard, "solo numeri", 8.00));
            add(new EItem(itemType.Processor, "piccolo folletto che fa i calcoli", 111.11));
        }};
        try{
            assertEquals(371.61, new EShopBill(lista, xida, new Date()).getOrderPrice(lista, xida), 0.01);
        }
        catch(BillException e){
            assertEquals(false, true);
        }
    }

    @Test
    public void getOrderPriceWithProcessorAndMouseDiscount(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "medio", 20.50));
            add(new EItem(itemType.Mouse, "gigante", 100.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "medio", 20.50));
            add(new EItem(itemType.Mouse, "gigante", 100.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "medio", 20.50));
            add(new EItem(itemType.Mouse, "gigante", 100.50));
            add(new EItem(itemType.Mouse, "gigantissimo", 200.00));
            add(new EItem(itemType.Processor, "m2", 0.50));
            add(new EItem(itemType.Keyboard, "meccanica", 20.00));
            add(new EItem(itemType.Processor, "piccolo folletto che fa i calcoli", 111.11));
            add(new EItem(itemType.Processor, "m1", 0.25));
            add(new EItem(itemType.Processor, "i1", 10.00));
            add(new EItem(itemType.Processor, "non so più", 5.00));
            add(new EItem(itemType.Processor, "processore anonimo", 3.00));
        }};
        try{
            assertEquals(733.735, new EShopBill(lista, xida, new Date()).getOrderPrice(lista, xida), 0.01);
        }
        catch(BillException e){
            assertEquals(false, true);
        }
    }
}
