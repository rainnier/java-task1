package com.codility;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class VisitCounterTest {

	@Test
	void testInputIsNull() {
		VisitCounter vc = new VisitCounter();
		assert(vc.count(null).isEmpty());
	}
	
	@Test
	void testInputIsEmptyMap() {
		VisitCounter vc = new VisitCounter();
		assert(vc.count().isEmpty());
	}
	
	@Test
	void testInputIsEmptyMaps() {
		VisitCounter vc = new VisitCounter();
		assert(vc.count(new HashMap(), new HashMap()).isEmpty());
	}
	
	@Test
	void testMapKeyParseableToLong() {
		VisitCounter vc = new VisitCounter();
		Map<String, UserStats> m = new HashMap<>();
		UserStats u = new UserStats();
		u.setVisitCount(5l);
		m.put("123", u);
		
		u = new UserStats();
		u.setVisitCount(7l);
		m.put("12k", u);
		
		//assert(vc.count(m) == 1);
	}
	
	@Test
	void testMapKeyNotParseableToLong() {
		fail("Not yet implemented");
	}
	
	@Test
	void testMapUserStatsIsNull() {
		fail("Not yet implemented");
	}

}
