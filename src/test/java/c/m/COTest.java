package c.m;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import c.m.system.CO;
import c.m.system.DataProvider;

public class COTest {
	CO sut = new CO();

	@Test
	public void mustGiveProperOutputsForInput() {
		assertArrayEquals(DataProvider.getTarget(), sut.output(DataProvider.getInput()));
	}

	@Test
	public void mustGiveProperInfluencedOutputsForInput() {
		sut.influenceF();
		assertArrayEquals(DataProvider.getInfluencedTarget(), sut.output(DataProvider.getInput()));
	}
}
