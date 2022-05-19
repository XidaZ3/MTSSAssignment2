package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EItemTest {

    @Test
    public void getTypeTest(){
        EItem i7 = new EItem(itemType.Processor, "i7", 25.00);
        assertEquals(itemType.Processor, i7.getType());
    }
    
    @Test
    public void getNameTest(){
        EItem i7 = new EItem(itemType.Processor, "i7", 25.00);
        assertEquals("i7", i7.getName());
    }
    
    @Test
    public void getPriceTest(){
        EItem i7 = new EItem(itemType.Processor, "i7", 25.00);
        assertEquals(25.00, i7.getPrice(), 0.0);
    }
    
}
