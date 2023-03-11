package com.custom.datastructures.graphs;

import java.util.*;

public class ShortestPathBetweenTwoVerticesInUndirectedGraph {

    public static List<Integer> shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraph(int n , int[][] edges, int source, int destination){
        Map<Integer, List<Integer>> adjacencyList = prepareAdjacencyListForGraph(n,edges);
        //Do BFS traversal to find parent of all nodes (BFS return parents with shortest path)
        boolean[] visited = new boolean[n+1];
        Map<Integer,Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        //push source node to queue for BFS
        queue.offer(source);
        //mark source visited
        visited[source] = true;
        //put parent of source
        parent.put(source, null);


        //Generate parent mapping going in BFS manner from source to destination
        while(!queue.isEmpty()){
            Integer front = queue.poll();
            for(int neighbour : adjacencyList.get(front)){
                if(!visited[neighbour]){
                    queue.offer(neighbour);
                    visited[neighbour] = true;
                    parent.put(neighbour, front);
                }
            }
        }
        //now generate the path from destination to source and reverse the path
        int currNode = destination, newDest = source;
        ArrayList<Integer> shortestPath = new ArrayList<>();
        // this will give path from dest to source
        while(currNode != source){
            shortestPath.add(currNode);
            currNode = parent.get(currNode);
        }
        shortestPath.add(currNode);

        //now reverse this
        reverse(shortestPath);

        return shortestPath;
    }
    private static Map<Integer, List<Integer>> prepareAdjacencyListForGraph(int n , int[][] edges){
        Map<Integer,List<Integer>> adjancecyList = new HashMap<>();
        for(int j = 0; j< edges.length;j++){
            int U = edges[j][0], V = edges[j][1];
            //U to V
            adjancecyList.put(U, adjancecyList.getOrDefault(U, new ArrayList<>()));
            adjancecyList.get(U).add(V);
            //V to U
            adjancecyList.put(V, adjancecyList.getOrDefault(V, new ArrayList<>()));
            adjancecyList.get(V).add(U);
        }

        // all nodes which are not added defined in edges should have empty list
        for(int i = 1; i <= n;i++){
            if(!adjancecyList.containsKey(i))
                adjancecyList.put(i, new ArrayList<>());
        }
        return adjancecyList;
    }

    private static void reverse(ArrayList<Integer> list){
        for(int i = 0;i< list.size()/2;i++){
            int tmp = list.get(i);
            list.set(i, list.get(list.size()-1-i));
            list.set(list.size()-1-i, tmp);
        }


    }

    public static void main(String[] args) {
            int[][] edges= {{1,2},{1,3},{1,4},{2,5},{3,8},{4,6},{5,8},{6,7},{7,8}};
            List<Integer> shortestPath = shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraph(8,edges,1,8);
            System.out.println(shortestPath);
    }
}
