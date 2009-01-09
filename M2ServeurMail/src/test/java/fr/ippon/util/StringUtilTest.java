package fr.ippon.util;

import junit.framework.TestCase;


public class StringUtilTest extends TestCase{

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testIsEmpty(){
		String chaine = "";
		assertTrue(StringUtil.isEmpty(chaine));
	}
	
}
