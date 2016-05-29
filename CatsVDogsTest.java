import org.junit.gen5.api.Disabled;
import org.junit.gen5.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.gen5.api.Assertions.assertEquals;

public class CatsVDogsTest {

    @Test
    @Disabled
    public void testCatsVDogs() {
        CatsVDogs.TestCase testCase1 = new CatsVDogs.TestCase();
        testCase1.cats = 1;
        testCase1.dogs = 1;
        testCase1.voters = 2;
        testCase1.votes = new String[][]{
                {"C1", "D1"},
                {"D1", "C1"}
        };

        CatsVDogs.TestCase testCase2 = new CatsVDogs.TestCase();
        testCase2.cats = 1;
        testCase2.dogs = 2;
        testCase2.voters = 4;
        testCase2.votes = new String[][]{
                {"C1", "D1"},
                {"C1", "D1"},
                {"C1", "D2"},
                {"D2", "C1"}
        };

        List<Integer> results = CatsVDogs.calculateMaxSatisfiedVoters(Arrays.asList(testCase1, testCase2));

        assertEquals(1, results.size());

        assertEquals(1, results.get(0));
        assertEquals(3, results.get(1));
    }
}