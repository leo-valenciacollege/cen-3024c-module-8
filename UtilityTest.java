package cen3024c;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilityTest {

	@Test
	void testRandomNumbersValues() {
		
		Utility utility = new Utility();
        int size = 200000000;
        int[] randomNumbers = utility.randomNumbers(size);
        
        //check if values in array are between 1 - 10
        for (int num : randomNumbers) {
            assertTrue(num >= 1 && num <= 10);
        }
	}

}
