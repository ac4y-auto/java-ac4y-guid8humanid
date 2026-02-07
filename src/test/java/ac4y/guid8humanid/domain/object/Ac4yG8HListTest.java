package ac4y.guid8humanid.domain.object;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Ac4yG8HListTest {

    private Ac4yG8HList ac4yG8HList;

    @Before
    public void setUp() {
        ac4yG8HList = new Ac4yG8HList();
    }

    @Test
    public void testDefaultConstructorInitializesList() {
        assertNotNull(ac4yG8HList.getAc4yG8H());
        assertTrue(ac4yG8HList.getAc4yG8H() instanceof ArrayList);
        assertEquals(0, ac4yG8HList.getAc4yG8H().size());
    }

    @Test
    public void testAddElement() {
        Ac4yG8H g8h = new Ac4yG8H(
                1,
                "guid-1",
                "human.id.1",
                "id.1",
                "humanid1",
                "template-guid",
                1
        );

        ac4yG8HList.getAc4yG8H().add(g8h);

        assertEquals(1, ac4yG8HList.getAc4yG8H().size());
        assertEquals(g8h, ac4yG8HList.getAc4yG8H().get(0));
    }

    @Test
    public void testAddMultipleElements() {
        Ac4yG8H g8h1 = new Ac4yG8H(1, "guid-1", "id-1", "id-1", "id1", "template", 1);
        Ac4yG8H g8h2 = new Ac4yG8H(2, "guid-2", "id-2", "id-2", "id2", "template", 1);
        Ac4yG8H g8h3 = new Ac4yG8H(3, "guid-3", "id-3", "id-3", "id3", "template", 1);

        ac4yG8HList.getAc4yG8H().add(g8h1);
        ac4yG8HList.getAc4yG8H().add(g8h2);
        ac4yG8HList.getAc4yG8H().add(g8h3);

        assertEquals(3, ac4yG8HList.getAc4yG8H().size());
        assertEquals(g8h1, ac4yG8HList.getAc4yG8H().get(0));
        assertEquals(g8h2, ac4yG8HList.getAc4yG8H().get(1));
        assertEquals(g8h3, ac4yG8HList.getAc4yG8H().get(2));
    }

    @Test
    public void testSetList() {
        List<Ac4yG8H> newList = new ArrayList<>();
        newList.add(new Ac4yG8H(1, "guid-1", "id-1", "id-1", "id1", "template", 1));

        ac4yG8HList.setAc4yG8H(newList);

        assertEquals(newList, ac4yG8HList.getAc4yG8H());
        assertEquals(1, ac4yG8HList.getAc4yG8H().size());
    }

    @Test
    public void testClearList() {
        ac4yG8HList.getAc4yG8H().add(new Ac4yG8H(1, "guid", "id", "id", "id", "template", 1));
        assertEquals(1, ac4yG8HList.getAc4yG8H().size());

        ac4yG8HList.getAc4yG8H().clear();
        assertEquals(0, ac4yG8HList.getAc4yG8H().size());
    }
}
