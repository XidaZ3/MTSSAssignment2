package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class UserImplTest {

    @Test
    public void getIdTest(){
        UserImpl xida = new UserImpl(0, "xida", 34);
        assertEquals(0, xida.getId());
    }

    @Test
    public void getNameTest(){
        UserImpl xida = new UserImpl(0, "xida", 34);
        assertEquals("xida", xida.getName());
    }

    @Test
    public void getAgeTest(){
        UserImpl xida = new UserImpl(0, "xida", 34);
        assertEquals(34, xida.getAge());
    }
    
}
