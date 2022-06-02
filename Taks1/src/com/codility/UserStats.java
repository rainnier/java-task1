package com.codility;

import java.util.Optional;

public class UserStats {
	Optional<Long> visitCount;
	
	Optional<Long> getVisitCount() {
		return visitCount;
	}
	
	Optional<Long> setVisitCount(Long visitCount) {
		return this.visitCount = Optional.of(visitCount);
	}
	
}
