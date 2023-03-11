package com.custom.datastructures.graphs;

import java.util.*;
import java.util.Stack;

public class ShortestPathToAllVerticesFromSourceInDirectedAcyclicGraph {

    // O(E+ V) time O(V) space
    public static int[] shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraphUsingTopologicalSort(int n , int[][] edges, int source){
        //return the shortest distance from source to all nodes i.e. array of the shortest distance from source to all vertices
        int [] distance = new int[n];
        //initialise with INT MAX as distance
        Arrays.fill(distance,Integer.MAX_VALUE);

        List<Neighbour>[] adjacencyList = prepareAdjacencyListForGraph(n, edges);
        Stack<Integer> topologicalSortStack = new Stack<>();
        boolean[] visited = new boolean[n];
        // generate topological sort order. In this order all nodes left to source are unreachable,
        // thus we will only process the path where nodes are reachable from source in a proper order
        dfsForTopologicalSort(adjacencyList,source,visited,topologicalSortStack);

        //source distance
        distance[source] = 0;

        while (!topologicalSortStack.isEmpty()){
            int peek = topologicalSortStack.pop();
            //if its reachable node
            if(distance[peek] != Integer.MAX_VALUE){
                //for all neighbours
                for(Neighbour neighbour : adjacencyList[peek]){
                    int adjacentNode = neighbour.val, adjacentDistance = neighbour.distance;
                    // distance to reach neighbour from source via peek = distance to reach peek  from source + distance to reach neighbour from peek
                    //update the distance for neighbour, if the distance is shorter
                    if(distance[adjacentNode] > distance[peek]+ adjacentDistance){
                        distance[adjacentNode] = distance[peek]+ adjacentDistance;
                    }
                }
            }
        }

        return distance;
    }

    // O((E+ V)*LOG V ) time O(V) space, Dijkstra’s algorithm doesn’t work for graphs with negative weight edges
    public static int[] shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraphUsingDijkistraAlgorigthm(int [][] edges, int n,int source){
        int [] distance = new int[n];
        //initialise with INT MAX as distance
        Arrays.fill(distance,Integer.MAX_VALUE);

        List<Neighbour>[] adjacencyList = prepareAdjacencyListForGraph(n, edges);
        PriorityQueue<Neighbour> minHeap = new PriorityQueue<>((a,b)-> a.distance- b.distance );
        //add source node in minHeap
        minHeap.offer(new Neighbour(source, 0));
        //distance to source is 0
        distance[source] = 0;

        while (!minHeap.isEmpty()){
            //poll here is processing and removing the node, its like processing and marking visited = true
            Neighbour peek = minHeap.poll();
            //for all neighbours
            for(Neighbour neighbour: adjacencyList[peek.val]){
                int adjacentNode = neighbour.val, adjacentDistance = neighbour.distance;

                if(distance[adjacentNode] > distance[peek.val]+ adjacentDistance){
                    distance[adjacentNode] = distance[peek.val]+ adjacentDistance;
                    //add this neighbour in minHeap with the updated distance
                    minHeap.offer(new Neighbour(adjacentNode,distance[adjacentNode]));
                }
            }
        }

        return distance;
    }

    private static void dfsForTopologicalSort(List<Neighbour>[] adjacencyList, int node, boolean[] visited, Stack<Integer> topologicalSortStack){
        visited[node] = true;
        for( Neighbour neighbour : adjacencyList[node]){
           if(!visited[neighbour.val]){
               dfsForTopologicalSort(adjacencyList,neighbour.val, visited,topologicalSortStack);
           }
        }
        topologicalSortStack.push(node);
    }

    private static List<Neighbour>[] prepareAdjacencyListForGraph(int n , int[][] edges){
        List<Neighbour>[] adjancecyList = new List[n];
        // initialize all nodes should have empty list
        for(int i = 0; i < n;i++){
            if(adjancecyList[i] == null)
                adjancecyList[i] = new ArrayList<>();
        }
        // for all edges create adjacency list
        for(int j = 0; j< edges.length;j++){
            int U = edges[j][0], V = edges[j][1], dist = edges[j][2];
            adjancecyList[U].add(new Neighbour(V,dist));
        }


        return adjancecyList;
    }

    static class Neighbour{
        int  val;
        int distance;

        public Neighbour(int val, int distance){
            this.val = val;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        int [][] edges = {{1,3,6}, {1,2,2}, {0,1,5},{0,2,3},{3,4,-1},{2,4,4},{2,5,2},{2,3,7},{4,5,-2}};

        int [][] edges1 = {{0,1,4},{0,7,8},{1,2,8},{1,7,11},{1,0,7},{2,1,8},{2,3,7},{2,8,2},{2,5,4},{3,2,7},{3,4,9},
                {3,5,14},{4,5,10},{5,4,10},{5,6,2},{6,5,2},{6,7,1},{6,8,6},{7,0,8} ,{7,1,11},{7,6,1},{7,8,7},{8,2,2},{8,6,6},{8,7,1}};

        int distance[] = shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraphUsingTopologicalSort(6,edges,1);
        print(distance);
        System.out.println("Using Dijkstra Algorithm");
        distance = shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraphUsingDijkistraAlgorigthm( edges,6, 1);
        print(distance);

        distance = shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraphUsingTopologicalSort(9,edges1,0);
        print(distance);
        System.out.println("Using Dijkstra Algorithm");
        distance = shortestPathBetweenTwoVerticesInUnWeightedUnDirectedGraphUsingDijkistraAlgorigthm( edges1,9, 0);
        print(distance);


    }

    private static void print(int[] arr){
        for(int el: arr)
            System.out.print(el+" ");
        System.out.println();
    }
}
