package com.codility;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeTwoMaps {

    public static void main(String[] args) {
        Map<String, UserStats> map = new HashMap<>();

        UserStats u = new UserStats();
        
        u.setVisitCount(3L);
        map.put("123", u);
        u = new UserStats();
        u.setVisitCount(5L);
        map.put("345", u);

        Map<String, UserStats> map1 = new HashMap<>();

        u = new UserStats();
        u.setVisitCount(8L);
        map1.put("123", u);
        u = new UserStats();
        u.setVisitCount(5L);
        map1.put("2", u);


        Map<Long, Long> resultMap = Stream.concat(map.entrySet().stream(), map1.entrySet().stream())
                .collect(Collectors.toMap(t -> Long.valueOf(t.getKey()), t -> t.getValue().visitCount.get(),
                        (u1, u2) -> u1 + u2)
                		);

        System.out.println(resultMap);
    }
}