package com.codility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class VisitCounter2 {

	Map<Long, Long> count(Map<String, UserStats>... visits) {

		Map<Long, Long> m = new HashMap<>();
		if (visits == null || visits.length == 0) {
			return m;
		}

		m = Arrays.asList(visits).stream().reduce((map1, map2) -> {
			return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream()).filter(t -> t.getKey() != null)
					.filter(t -> {
						try {
							Long.valueOf(t.getKey());
						} catch(Exception e) {
							return false;
						}
						return true;
					})
					.filter(t -> t.getValue() != null)
					.filter(t -> t.getValue().visitCount != null).filter(t -> t.getValue().visitCount.isPresent())
					.filter(t -> !t.getValue().visitCount.isEmpty())
					.collect(Collectors.toMap(t -> t.getKey(), t -> t.getValue(), (u1, u2) -> {
						u1.setVisitCount(u1.visitCount.orElse(0L).longValue() + u2.visitCount.orElse(0L));
						return u1;
					}));
		}).get().entrySet().stream().collect(
				Collectors.toMap(t -> Long.valueOf(t.getKey()), t -> Long.valueOf(t.getValue().visitCount.orElse(0L))));

		System.out.print(m);

		return m;
	}

	private UserStats merge(UserStats u1, UserStats u2) {
		u2.setVisitCount(u1.getVisitCount().orElse(0L).longValue() + u2.getVisitCount().orElse(0L).longValue());
		return u2;
	}

	public static void main(String args[]) {

		VisitCounter2 vc = new VisitCounter2();
		Map<String, UserStats> m = new HashMap<>();

		UserStats u = new UserStats();
		u.setVisitCount(5L);
		m.put("123", u);
		m.put(null, u);

		u = new UserStats();
		u.setVisitCount(7L);
		m.put("12", null);

		Map<String, UserStats> m1 = new HashMap<>();
		u = new UserStats();
		u.setVisitCount(3L);
		m1.put("12", u);

		u = new UserStats();
		u.setVisitCount(2L);
		m1.put("15", u);

		vc.count(m, m1);

		Map<String, UserStats> map1 = new HashMap<String, UserStats>();
		Map<String, UserStats> map2 = new HashMap<String, UserStats>();

		Map<Long, Long> k = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
				.collect(Collectors.toMap(t -> Long.valueOf(t.getKey()),
						t -> t.getValue().visitCount.orElse(0L).longValue(), (u1, u2) -> u1 + u2));

	}
}