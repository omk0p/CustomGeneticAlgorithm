package c.m;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import c.m.system.CO;
import c.m.system.DataBusket;
import c.m.system.DataProvider;

public class COTest {
	CO sut = new CO();

	@Test
	public void mustGiveProperOutputsForInput() {
		assertArrayEquals(DataProvider.getTarget(), sut.output(DataProvider.getInput()));
	}
}
