package com.liambass.main.dto;

import org.junit.Test;


import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class FilmDTOTest {


	@Test
	public void testEquals() {
		EqualsVerifier.forClass(FilmDTO.class)
		.suppress(Warning.NONFINAL_FIELDS)
		.usingGetClass()
		.verify();
	}
}
