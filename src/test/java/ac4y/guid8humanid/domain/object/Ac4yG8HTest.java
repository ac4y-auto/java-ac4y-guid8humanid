package ac4y.guid8humanid.domain.object;

import ac4y.base.Ac4yException;
import ac4y.base.domain.Ac4yClass;
import ac4y.base.domain.Ac4yIdentification;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Ac4yG8HTest {

    private Ac4yG8H ac4yG8H;

    @Before
    public void setUp() {
        ac4yG8H = new Ac4yG8H(
                1,
                "test-guid-123",
                "test.humanid",
                "humanid",
                "testhumanid",
                "template-guid-456",
                2
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1, ac4yG8H.getPersistentID());
        assertEquals("test-guid-123", ac4yG8H.getGUID());
        assertEquals("test.humanid", ac4yG8H.getHumanID());
        assertEquals("humanid", ac4yG8H.getPublicHumanID());
        assertEquals("testhumanid", ac4yG8H.getSimpledHumanID());
        assertEquals("template-guid-456", ac4yG8H.getTemplateGUID());
        assertEquals(2, ac4yG8H.getTemplatePersistentID());
    }

    @Test
    public void testSetters() {
        ac4yG8H.setPersistentID(10);
        ac4yG8H.setGUID("new-guid");
        ac4yG8H.setHumanID("new.id");
        ac4yG8H.setPublicHumanID("id");
        ac4yG8H.setSimpledHumanID("newid");
        ac4yG8H.setTemplateGUID("new-template");
        ac4yG8H.setTemplatePersistentID(20);
        ac4yG8H.setDeleted(true);

        assertEquals(10, ac4yG8H.getPersistentID());
        assertEquals("new-guid", ac4yG8H.getGUID());
        assertEquals("new.id", ac4yG8H.getHumanID());
        assertEquals("id", ac4yG8H.getPublicHumanID());
        assertEquals("newid", ac4yG8H.getSimpledHumanID());
        assertEquals("new-template", ac4yG8H.getTemplateGUID());
        assertEquals(20, ac4yG8H.getTemplatePersistentID());
        assertTrue(ac4yG8H.isDeleted());
    }

    @Test
    public void testGetAc4yIdentification() throws Ac4yException {
        Ac4yIdentification identification = ac4yG8H.getAc4yIdentification();

        assertNotNull(identification);
        assertEquals(1, identification.getPersistentID());
        assertEquals("test-guid-123", identification.getGUID());
        assertEquals("test.humanid", identification.getHumanID());
        assertEquals("humanid", identification.getPublicHumanID());
        assertEquals("template-guid-456", identification.getTemplateGUID());
    }

    @Test
    public void testGetAc4yClass() throws Ac4yException {
        Ac4yClass ac4yClass = ac4yG8H.getAc4yClass();

        assertNotNull(ac4yClass);
        assertEquals(1, ac4yClass.getPersistentID());
        assertEquals("test-guid-123", ac4yClass.getGUID());
        assertEquals("test.humanid", ac4yClass.getHumanID());
        assertEquals("humanid", ac4yClass.getPublicHumanID());
    }

    @Test(expected = Ac4yException.class)
    public void testGetAc4yIdentificationFromAc4yG8HWithNull() throws Ac4yException {
        ac4yG8H.getAc4yIdentificationFromAc4yG8H(null);
    }

    @Test(expected = Ac4yException.class)
    public void testGetAc4yClassFromAc4yG8HWithNull() throws Ac4yException {
        ac4yG8H.getAc4yClassFromAc4yG8H(null);
    }

    @Test
    public void testDefaultConstructor() {
        Ac4yG8H emptyG8H = new Ac4yG8H();
        assertNotNull(emptyG8H);
    }

    @Test
    public void testDeletedFlag() {
        assertFalse(ac4yG8H.isDeleted());
        ac4yG8H.setDeleted(true);
        assertTrue(ac4yG8H.isDeleted());
        ac4yG8H.setDeleted(false);
        assertFalse(ac4yG8H.isDeleted());
    }
}
