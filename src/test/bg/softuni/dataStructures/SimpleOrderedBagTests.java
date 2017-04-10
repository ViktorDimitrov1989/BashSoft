package test.bg.softuni.dataStructures;

import main.bg.softuni.contracts.SimpleOrderedBag;
import main.bg.softuni.dataStructure.SimpleSortedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleOrderedBagTests {

    private static final String ROSEN_NAME_TO_ADD = "Rosen";
    private static final String GEORGI_NAME_TO_ADD = "Georgi";
    private static final String BALKAN_NAME_TO_ADD = "Balkan";
    private static final int EXPECTED_SIZE = 17;
    private static final int EXPECTED_CAPACITY = 16;
    private static final int ACTUAL_SIZE_AFTER_ADDALL_METHOD = 2;
    private static final String ELEMENT_TO_ADD_AND_REMOVE = "Pesho";
    private static final String EXPECTED_STRING_AFTER_JOINING = "Balkan, Georgi, Rosen";
    private final String[] EXPECTED_ORDERED_NAMES = new String[]{"Balkan","Georgi", "Rosen"};
    private final List<String> LIST_OF_NAMES_TO_ADD = new ArrayList<>(Arrays.asList("Rosen", "Georgi", "Balkan"));
    private final String[] EXPECTED_ORDERED_LIST_OF_NAMES = new String[]{"Balkan","Georgi", "Rosen"};
    private List<String> HARDCODED_LIST = new ArrayList<>(Arrays.asList(ROSEN_NAME_TO_ADD, GEORGI_NAME_TO_ADD));

    private SimpleOrderedBag<String> names;

    @Before
    public void setUp(){
        this.names = new SimpleSortedList<String>(String.class);
    }

    @Test
    public void testEmptyCtor(){
        this.names = new SimpleSortedList<String>(String.class);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithInitialCapacity(){
        this.names = new SimpleSortedList<String>(String.class,
                String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithAllParams(){
        this.names = new SimpleSortedList<String>(String.class,
                String.CASE_INSENSITIVE_ORDER,
                30);

        Assert.assertEquals(30, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testAddIncreaseSize(){
        this.names.add("Nasko");
        Assert.assertEquals(1, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullThrowsException(){
        this.names.add(null);
    }

    @Test
    public void testAddUnsortedDetailsHeldStored(){
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(GEORGI_NAME_TO_ADD);
        this.names.add(BALKAN_NAME_TO_ADD);

        int expectedIndex = 0;

        for (String name : this.names) {
            Assert.assertEquals(EXPECTED_ORDERED_NAMES[expectedIndex++], name);
        }
    }

    @Test
    public void testAddingMoreThanInitialCapacity(){
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);

        Assert.assertEquals(this.names.size(), EXPECTED_SIZE);
        Assert.assertNotEquals(this.names.capacity(), EXPECTED_CAPACITY);
    }

    @Test
    public void testAddingAllFromCollectionIncreaseSize(){
        this.names.addAll(HARDCODED_LIST);

        Assert.assertEquals(this.names.size(), ACTUAL_SIZE_AFTER_ADDALL_METHOD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingAllFromNullThrowsException(){
        this.names.addAll(null);
    }

    @Test
    public void testAddAllKeepsCollectionSorted(){
        this.names.addAll(LIST_OF_NAMES_TO_ADD);

        int expectedArrIndex = 0;

        for (String name : this.names) {
            Assert.assertEquals(name, EXPECTED_ORDERED_LIST_OF_NAMES[expectedArrIndex++]);
        }
    }

    @Test
    public void testRemoveValidElementDecreaseSize(){
        this.names.add(ELEMENT_TO_ADD_AND_REMOVE);
        this.names.remove(ELEMENT_TO_ADD_AND_REMOVE);

        Assert.assertEquals(0,this.names.size());
    }

    @Test
    public void testRemoveValidElementRemovesSelectedOne(){
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(GEORGI_NAME_TO_ADD);

        this.names.remove(GEORGI_NAME_TO_ADD);

        for (String name : this.names) {
            Assert.assertEquals(name, ROSEN_NAME_TO_ADD);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullThrowsException(){
        this.names.add(GEORGI_NAME_TO_ADD);

        this.names.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJoinWithNull(){
        this.names.add(GEORGI_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(BALKAN_NAME_TO_ADD);

        String res = this.names.joiWith(null);

    }

    @Test
    public void testJoinWorksFine(){
        this.names.add(GEORGI_NAME_TO_ADD);
        this.names.add(ROSEN_NAME_TO_ADD);
        this.names.add(BALKAN_NAME_TO_ADD);

        Assert.assertEquals(EXPECTED_STRING_AFTER_JOINING, this.names.joiWith(", "));

    }

}
