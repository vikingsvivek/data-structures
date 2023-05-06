package com.custom.datastructures.graphs;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumSpanningTree {
    //Spanning Tree is a tree representation of a graph, connecting N nodes with N-1 edges. i.e. All nodes in graph are reachable in spanning tree
    //Minimum spanning tree(MST) is one of spanning tree, which is having the each edge in spanning tree having the min possible weight
    // i.e. we can connect all nodes in MST with min distance

    //O(V*LogV+ E) time O(V) space
    private static ArrayList<ArrayList<Integer>> minimumSpanningTreePrimsAlgorithmOptimizedUsingMinHeap(int n,int m, ArrayList<ArrayList<Integer>> graph){

        List<Node>[] adjacencyList = prepareAdjacencyListForGraph(n,graph);

        //shortest distance for nodes
        int[] distance = new int[n+1];

        // track parent of ith node in this array
        int [] parent = new int[n+1];
        //track if node is included in MST or not
        boolean mst[] = new boolean[n+1];

        //initialise distance with INT MAX
        Arrays.fill(distance,Integer.MAX_VALUE);
        //initialize parent with -1
        Arrays.fill(parent,-1);

        //Assuming 1 as source, its distance is marked 0
        distance[1] = 0;

        //Min Heap for min values
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a,b)-> a.distance - b.distance);
        //add source distance in heap
        minHeap.offer(new Node(1, distance[1]));

        while(!minHeap.isEmpty()){
            //U is min distance node which not included in MST
            Node minNode = minHeap.poll();
            int U = minNode.val;
            //mark this U as included in MST
            mst[U] = true;
            // process the neighbours of U //O(E) time
            for(Node V : adjacencyList[U]){
                int neighbour = V.val;
                int weight = V.distance;
                if(mst[neighbour] == false && weight < distance[neighbour]){
                    //set parent of V
                    parent[neighbour] = U;
                    //update distance
                    distance[neighbour] = weight;
                    //put neighbour in minHeap
                    minHeap.offer(new Node(neighbour, distance[neighbour]));
                }
            }

        }

        //create MST using parent array
        ArrayList<ArrayList<Integer>> mstEdges = new ArrayList<>();


        //starting from 2 because we assumed 1 as source and its parent is -1
        for(int i = 2; i < parent.length;i++){
            //add parent child relation as edge in MST
            mstEdges.add(Arrays.stream(new int[]{parent[i],i, distance[i]}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        }


        return mstEdges;
    }

    //O(V*V+ E) time O(V) space
    private static ArrayList<ArrayList<Integer>> minimumSpanningTreePrimsAlgorithm(int n,int m, ArrayList<ArrayList<Integer>> graph){

        List<Node>[] adjacencyList = prepareAdjacencyListForGraph(n,graph);

        //shortest distance for nodes
        int[] distance = new int[n+1];

        // track parent of ith node in this array
        int [] parent = new int[n+1];
        //track if node is included in MST or not
        boolean mst[] = new boolean[n+1];

        //initialise distance with INT MAX
        Arrays.fill(distance,Integer.MAX_VALUE);
        //initialize parent with -1
        Arrays.fill(parent,-1);

        //Assuming 1 as source, its distance is marked 0
        distance[1] = 0;

        for(int i = 1; i <= n;i++){
            //U is min distance node which not included in MST
            int U = i;
            int min = Integer.MAX_VALUE;
            //find min distance node which is not in MST currently (i.e. not processed)
            for(int v = 1; v <= n;v++){
                //if is minimum and not added in MST, update the min
                if(mst[v] == false && min > distance[v]){
                    U = v;
                    min = distance[v];
                }
            }

            //mark this U as included in MST
            mst[U] = true;
            // process the neighbours of U //O(E) time
            for(Node V : adjacencyList[U]){
                int neighbour = V.val;
                int weight = V.distance;
                if(mst[neighbour] == false && weight < distance[neighbour]){
                    //set parent of V
                    parent[neighbour] = U;
                    //update distance
                    distance[neighbour] = weight;
                }
            }

        }

        //create MST using parent array
        ArrayList<ArrayList<Integer>> mstEdges = new ArrayList<>();


        //starting from 2 because we assumed 1 as source and its parent is -1
        for(int i = 2; i < parent.length;i++){
            //add parent child relation as edge in MST
            mstEdges.add(Arrays.stream(new int[]{parent[i],i, distance[i]}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        }


        return mstEdges;
    }


    private static List<Node>[] prepareAdjacencyListForGraph(int n , ArrayList<ArrayList<Integer>> edges){
        // assuming vertices are numbered from 1 to N
        List<Node>[] adjancecyList = new List[n+1];
        // initialize all nodes should have empty list
        for(int i = 1; i <= n;i++){
            adjancecyList[i] = new ArrayList<>();
        }

        // for all edges create adjacency list
        for(int j = 0; j< edges.size();j++){
            int U = edges.get(j).get(0), V = edges.get(j).get(1), dist = edges.get(j).get(2);
            //U to V
            adjancecyList[U].add(new Node(V,dist));
            //V to U
            adjancecyList[V].add(new Node(U,dist));
        }


        return adjancecyList;
    }

    static class Node{
        int  val;
        int distance;

        public Node(int val, int distance){
            this.val = val;
            this.distance = distance;
        }
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.stream(new int[]{1, 2, 2}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{1, 4, 6}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{2, 1, 2}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{2, 3, 3}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{2, 4, 8}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{2, 5, 5}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{3, 2, 3}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{3, 5, 7}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{4, 1, 6}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{4, 2, 8}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{4, 5, 9}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{5, 2, 5}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{5, 3, 7}).boxed().collect(Collectors.toCollection(ArrayList::new)));
        edges.add(Arrays.stream(new int[]{5, 4, 9}).boxed().collect(Collectors.toCollection(ArrayList::new)));

        ArrayList<ArrayList<Integer>> mst = minimumSpanningTreePrimsAlgorithm(5,14, edges);
        print(mst);

        mst = minimumSpanningTreePrimsAlgorithmOptimizedUsingMinHeap(5,14,edges);
        print(mst);

        mst = minimumSpanningTreeUsingKruskalsAlogrithm(5,edges);
        print(mst);
    }

    // O(ELogE + ELogV) time O(E+V) space
    private static ArrayList<ArrayList<Integer>> minimumSpanningTreeUsingKruskalsAlogrithm(int n, ArrayList<ArrayList<Integer>> graph){
        //this approach uses DisJoint Set

        //Step1: create & initialize Disjoint set
        int[] parent = new int[n+1];
        int[] rank = new int[n+1];
        initializeDisjointSet(parent,rank, n);

        //Step2: Sort the edges list given in graph based on weight/distance
        Collections.sort(graph, (a,b)-> a.get(2) - b.get(2));

        //total distance of MST
        int totalDistance = 0;
        ArrayList<ArrayList<Integer>> MST = new ArrayList<>();
        //Step3: process all edges of the graph
        for(ArrayList<Integer> edge: graph){
            int U  = edge.get(0), V = edge.get(1), dist = edge.get(2);
            //find if both vertices doesn't belong to same component(i.e. joining them will not make a cycle) then add them together to form a component
            U = getParent(parent, U);
            V = getParent(parent,V);
            if( U != V){
                MST.add(edge);
                makeUnion(parent,rank, U, V );
                totalDistance += dist;
            }
        }

        System.out.println("TotalDistance of MST: "+totalDistance);
        //return  Min Spanning Tree
        return MST;
    }

    //O(1) constant time its assumed that it runs in O(4*A) time
    private static int getParent(int [] parent, int node){
        if(parent[node] == node)
            return node;
        //update the curr parent accordingly
        parent[node] = getParent(parent, parent[node]);
        return parent[node];
    }

    //O(1) constant time its assumed that it runs in O(4*A) time
    private static void makeUnion(int[] parent, int [] rank, int U, int V){
         //replace U & V  with their parents
         U = getParent(parent, U);
         V = getParent(parent, V);

        //compare ranks and update parent accordingly. this step is adding U in V or V in U based on their ranks. smaller rank node would be attached in bigger rank node.
        // smaller component is added in bigger component so that overall all merged component's depth is minimized.
        if(rank[U] < rank[V]){
            parent[U] = V;
        } else if (rank[U] > rank[V]) {
            parent[V] = U;
        }else {
            parent[V] = U;
            rank[U]++;
        }
    }

    private static void initializeDisjointSet(int[] parent, int [] rank, int vertices){
        // initially each node would be its own parent and having 0 rank
        for(int i = 1;i<=vertices;i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    private  static void print ( ArrayList<ArrayList<Integer>> edges){
        for(ArrayList<Integer> edge : edges ){
            System.out.println(edge.get(0)+ ", "+edge.get(1)+", "+edge.get(2));
        }
        System.out.println();
    }
}
