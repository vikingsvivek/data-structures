package com.custom.datastructures;

import java.util.*;

public class SamplesTest {
    public static void main(String[] args) {
        Map<Integer, Map<Integer, List<Integer>>> distance2LevelNodesMap = new TreeMap<>();

       /*
        distance2LevelNodesMap.getOrDefault(distance, new HashMap<>()).getOrDefault(level, new ArrayList<>()).add(val);
        distance2LevelNodesMap.getOrDefault(distance, new HashMap<>()).getOrDefault(level, new ArrayList<>()).add(val);
        distance2LevelNodesMap.getOrDefault(distance, new HashMap<>()).getOrDefault(level, new ArrayList<>()).add(val);
             */


         int distance[]= {-2,-1,0,1,2,-2,-2};
         int level[] ={0,1,1,2,2,2,2};
        int val [] = {1,2,3,4,5,6,7};

       for(int i = 0; i< distance.length;i++){
           Map<Integer,List<Integer>> disLevel= distance2LevelNodesMap.getOrDefault(distance[i], new HashMap<>());
           distance2LevelNodesMap.put(distance[i],disLevel);
           List<Integer> list = disLevel.getOrDefault(level[i], new ArrayList<>());
           disLevel.put(level[i],list);
           list.add(val[i]);
       }



        /*
        map.computeIfAbsent(1,HashMap::new).computeIfAbsent(1, ArrayList::new).add(1);
        map.computeIfAbsent(2,HashMap::new).computeIfAbsent(1, ArrayList::new).add(2);
        map.computeIfAbsent(-2,HashMap::new).computeIfAbsent(2, ArrayList::new).add(1);
        */
        System.out.println(distance2LevelNodesMap);
    }
}
