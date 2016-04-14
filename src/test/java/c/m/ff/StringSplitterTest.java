package c.m.ff;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringSplitterTest {

	String evenSampleBits = "10010100101110101010";
	String notEvenSampleBits = "110010100101110101010";
	int sampleParts = 4;
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void splitShouldSplitOnEqualPartsAndUseSign() {

		double[] expected = { -2, -2, -13, 10 };
		assertArrayEquals(expected, StringSplitter.signIntSplit(evenSampleBits, sampleParts), 0.0001);
	}

	@Test
	public void ifBitsCannotBeDividedEvenlyShouldThrowException() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Number of bits should be divided on parts without remain");
		StringSplitter.signIntSplit(notEvenSampleBits, sampleParts);
	}

}
