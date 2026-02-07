package ac4y.guid8humanid.domain.object;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Ac4yGUIDListTest {

    private Ac4yGUIDList ac4yGUIDList;

    @Before
    public void setUp() {
        ac4yGUIDList = new Ac4yGUIDList();
    }

    @Test
    public void testDefaultConstructorInitializesList() {
        assertNotNull(ac4yGUIDList.getGUID());
        assertTrue(ac4yGUIDList.getGUID() instanceof ArrayList);
        assertEquals(0, ac4yGUIDList.getGUID().size());
    }

    @Test
    public void testAddGUID() {
        ac4yGUIDList.getGUID().add("guid-1");

        assertEquals(1, ac4yGUIDList.getGUID().size());
        assertEquals("guid-1", ac4yGUIDList.getGUID().get(0));
    }

    @Test
    public void testAddMultipleGUIDs() {
        ac4yGUIDList.getGUID().add("guid-1");
        ac4yGUIDList.getGUID().add("guid-2");
        ac4yGUIDList.getGUID().add("guid-3");

        assertEquals(3, ac4yGUIDList.getGUID().size());
        assertEquals("guid-1", ac4yGUIDList.getGUID().get(0));
        assertEquals("guid-2", ac4yGUIDList.getGUID().get(1));
        assertEquals("guid-3", ac4yGUIDList.getGUID().get(2));
    }

    @Test
    public void testSetGUIDList() {
        List<String> newList = new ArrayList<>();
        newList.add("guid-a");
        newList.add("guid-b");

        ac4yGUIDList.setGUID(newList);

        assertEquals(newList, ac4yGUIDList.getGUID());
        assertEquals(2, ac4yGUIDList.getGUID().size());
    }

    @Test
    public void testClearList() {
        ac4yGUIDList.getGUID().add("guid-1");
        assertEquals(1, ac4yGUIDList.getGUID().size());

        ac4yGUIDList.getGUID().clear();
        assertEquals(0, ac4yGUIDList.getGUID().size());
    }

    @Test
    public void testContains() {
        ac4yGUIDList.getGUID().add("guid-test");

        assertTrue(ac4yGUIDList.getGUID().contains("guid-test"));
        assertFalse(ac4yGUIDList.getGUID().contains("guid-nonexistent"));
    }

    @Test
    public void testRemove() {
        ac4yGUIDList.getGUID().add("guid-1");
        ac4yGUIDList.getGUID().add("guid-2");

        assertEquals(2, ac4yGUIDList.getGUID().size());

        ac4yGUIDList.getGUID().remove("guid-1");

        assertEquals(1, ac4yGUIDList.getGUID().size());
        assertFalse(ac4yGUIDList.getGUID().contains("guid-1"));
        assertTrue(ac4yGUIDList.getGUID().contains("guid-2"));
    }
}
