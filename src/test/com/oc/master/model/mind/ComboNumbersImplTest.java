package test.com.oc.master.model.mind;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import main.com.oc.master.model.mind.ComboNumbersImpl;

class ComboNumbersImplTest {

	@Test
	void testCompareCombo() {

		ComboNumbersImpl comboNum = new ComboNumbersImpl();
		
		int[] source = {5,5,5,5};
		int[] target = {5,5,5,5};
		
		String result = "====";
		
		assertTrue("source equals to target/secret combo", (new String(comboNum.compareCombo(source, target))).equals(result));

		
	}
/*
	@Test (expected = NullPointerException.class)
	public final void testCompareComboNull() {
		
		
	}
	*/
}
