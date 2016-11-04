package main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by bbb1991 on 11/4/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class BoxTest {

    private static Box<String> box;
    private static Set<String> set;
    private static int counter = 0;

    @Before
    public void setUp() {
        box = new Box<>();
        set = new HashSet<>();
        set.addAll(Arrays.asList("One", "Two", "Three"));
    }


    @Test
    public void testAddElementsToEmptySet() {
        Assert.assertTrue(box.addElements(set));
    }

    @Test
    public void testAddElementsThatExistsInSet() {
        box.addElements(set);
        Assert.assertFalse(box.addElements(set));
    }

    @Test
    public void testMultiThreadWrite() {

    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
//                box.addElements(Str\.);
            }

        }
    }

}