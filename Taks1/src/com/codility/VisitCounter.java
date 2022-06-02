package com.codility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class VisitCounter {

	Map<Long, Long> count(Map<String, UserStats>... visits) {

		Map<Long, Long> m = new HashMap<>();
		if (visits == null || visits.length == 0) {
			return m;
		}

		Optional<Map<String, UserStats>> mk = Arrays.asList(visits).stream().reduce((map1, map2) -> {
			return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
					.filter(t -> t.getValue() != null)
					.filter(t -> makeKey(t) != null)
					.filter(t -> t.getValue().getVisitCount() != null && !t.getValue().getVisitCount().isEmpty())
					.collect(Collectors.toMap(t -> t.getKey(), t -> t.getValue(), (u1, u2) -> merge(u1, u2)));
		});

		Map<Long, Long> myMap = mk.get().entrySet().stream()
				.collect(Collectors.toMap(t -> Long.valueOf(t.getKey()),
				t -> (Long) t.getValue().visitCount.orElse(0L).longValue()));

		System.out.print(myMap);

		return m;
	}

	private String makeKey(Entry<String, UserStats> t) {
		try {
			Long.valueOf(t.getKey());
		} catch (Exception e) {
			return null;
		}
		return t.getKey();
	}

	private UserStats merge(UserStats u1, UserStats u2) {
		u2.setVisitCount(u1.getVisitCount().orElse(0L).longValue() + u2.getVisitCount().orElse(0L).longValue());
		return u2;
	}

	public static void main(String args[]) {

		VisitCounter vc = new VisitCounter();
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
		m1.put("12k", u);

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