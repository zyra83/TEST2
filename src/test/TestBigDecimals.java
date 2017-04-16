package test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;


public class TestBigDecimals {

	@Test
	public void test1() {
		System.out.println(0.1 * 0.1);
		
		// comme string, big decimal est immuable
		
		BigDecimal bg = new BigDecimal("0.1"); // préferer le constructeur string
		System.out.println(bg);
		
		bg = bg.multiply(new BigDecimal("0.1"));
		System.out.println(bg);
		
		Assert.assertEquals(true, true);
		
	}

}
