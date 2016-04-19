package c.m;

import org.junit.Test;

import c.m.system.DataProvider;

public class DataProviderTest {

	@Test
	public void transformToInputShouldWorkFine() {
		System.out.println(DataProvider.transformToDataBusket(DataProvider.getInput()));
	}

	@Test
	public void transformTargetShouldWorkFine() {
		System.out.println(DataProvider.transformToDataBusket(DataProvider.getTarget()));
	}
}
