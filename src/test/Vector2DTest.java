import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2DTest {
    @Test
    public void testequals(){
        Vector2D v1 = new Vector2D(0, 0);
        assertTrue(v1.equals(new Vector2D(0, 0)));
        assertFalse(v1.equals(new Vector2D(0, 1)));
        assertFalse(v1.equals(new Object()));
    }
    @Test
    public void testtoString(){
        Vector2D v1 = new Vector2D(0, 0);
        assertEquals(v1.toString(), "(0, 0)");
    }
    @Test
    public void testprecedes(){
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(2, 3);
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));
    }
    @Test
    public void testfollows(){
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(2, 3);
        assertTrue(v2.follows(v1));
        assertFalse(v1.follows(v2));
    }
    @Test
    public void testupperRight(){
        Vector2D v1 = new Vector2D(1, 4);
        Vector2D v2 = new Vector2D(2, 3);
        assertEquals(v1.upperRight(v2), new Vector2D(2, 4));
    }
    @Test
    public void testlowerLeft(){
        Vector2D v1 = new Vector2D(1, 4);
        Vector2D v2 = new Vector2D(2, 3);
        assertEquals(v1.lowerLeft(v2), new Vector2D(1, 3));
    }
    @Test
    public void testadd(){
        Vector2D v1 = new Vector2D(1, 1);
        Vector2D v2 = new Vector2D(1, 1);
        assertEquals(v1.add(v2), new Vector2D(2, 2));
    }
    @Test
    public void testsubtract(){
        Vector2D v1 = new Vector2D(1, 1);
        Vector2D v2 = new Vector2D(1, 1);
        assertEquals(v1.substract(v2), new Vector2D(0, 0));
    }
    @Test
    public void testopposite(){
        Vector2D v1 = new Vector2D(1, 1);
        assertEquals(v1.opposite(), new Vector2D(-1, -1));
    }
}
