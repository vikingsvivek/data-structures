package com.custom.datastructures.graphs;

import java.util.*;
import java.util.Stack;

public class Graph {

    public static Map<Integer,List<Integer>> prepareAdjacencyListForGraph(int[][] edges, boolean isUnDirected){
        Map<Integer,List<Integer>> adjancecyList = new HashMap<>();
            for(int j = 0; j< edges.length;j++){
                int U = edges[j][0], V = edges[j][1];
                adjancecyList.put(U, adjancecyList.getOrDefault(U, new ArrayList<>()));
                adjancecyList.get(U).add(V);

                // add another pair (v,u) if not directed graph
                if(isUnDirected){
                    adjancecyList.put(V, adjancecyList.getOrDefault(V, new ArrayList<>()));
                    adjancecyList.get(V).add(U);
                }
            }
        //System.out.println(adjancecyList);
        return adjancecyList;
    }

    //O(V+E) time O(V) space
     private List<List<Integer>> BFSTraversal(int[][] edges){
        // traverse all child/adjacent node of current nodes before moving further in the graph
        List<List<Integer>> componentPaths = new ArrayList<>();
        Map<Integer,Boolean> visited = new HashMap<>();
        Map<Integer, List<Integer>> adjacencyList = prepareAdjacencyListForGraph(edges, true);

        for(Map.Entry<Integer,List<Integer>> component : adjacencyList.entrySet()){
            if(!visited.containsKey(component.getKey())){
                // if not already visited
                List<Integer> path = bfs(component.getKey(),adjacencyList,visited);
                componentPaths.add(path);
            }
        }
        return componentPaths;
    }

    private List<Integer> bfs(int source,Map<Integer, List<Integer>> adjacencyList, Map<Integer,Boolean> visited){
        List<Integer> path = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        //add source to queue
        queue.offer(source);
        //mark source visited
        visited.put(source,true);

        while(!queue.isEmpty()){
            Integer front = queue.poll();
            //add front to ans
            path.add(front);
            for(Integer neighbour : adjacencyList.get(front)){
                // if neighbour is not visited
                if(!visited.containsKey(neighbour)){
                    //put neighbours in queue & mark them visited
                    queue.offer(neighbour);
                    visited.put(neighbour,true);
                }
            }
        }

        return path;
    }

    //O(V+E) time O(V) space
    private List<List<Integer>> DFSTraversal(int[][] edges){
        // traverse one child's path before moving to another child of current node in the graph
        List<List<Integer>> dfsT = new ArrayList<>();
        Map<Integer,Boolean> visited = new HashMap<>();
        Map<Integer, List<Integer>> adjacencyList = prepareAdjacencyListForGraph(edges, true);

        for(Map.Entry<Integer,List<Integer>> entry : adjacencyList.entrySet()){
            if(!visited.containsKey(entry.getKey())){
                List<Integer> path = new ArrayList<>();
                dfs(adjacencyList,visited,entry.getKey(),path);
                dfsT.add(path);
            }
        }
        return dfsT;
    }

    private void dfs(Map<Integer,List<Integer>> adjacencyList, Map<Integer,Boolean> visited, int vertex, List<Integer> path){
        path.add(vertex);
        visited.put(vertex,true);
            for(Integer neighbour: adjacencyList.get(vertex)){
                if(!visited.containsKey(neighbour)){
                    dfs(adjacencyList,visited,neighbour,path);
                }
            }
    }

    private boolean detectCycleInUnDirectedGraphUsingBFS(int[][]edges){
        Map<Integer, List<Integer>> adjacencyList = prepareAdjacencyListForGraph(edges, true);
        Map<Integer,Boolean> visited = new HashMap<>();

        for(Map.Entry<Integer,List<Integer>> source :  adjacencyList.entrySet()){
            if(!visited.containsKey(source.getKey())){
                 boolean isCyclic = detectCycleUnDirectedGraphUsingBFS(adjacencyList,source.getKey(), visited);
                 if(isCyclic)
                     return true;
                 //else check for other components
            }
        }
        return false;
    }

    private boolean detectCycleUnDirectedGraphUsingBFS(Map<Integer, List<Integer>> adjacencyList, int source, Map<Integer,Boolean> visited ){
        Map<Integer,Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        //add to queue
        queue.offer(source);
        //mark source visited
        visited.put(source, true);
        parent.put(source,-1);

        while (!queue.isEmpty()){
            int front = queue.poll();
            for(int neighbour : adjacencyList.get(front)){
                //if not already visited neighbour
                if(!visited.containsKey(neighbour)){
                    queue.add(neighbour);
                    //mark neighbour visited
                    visited.put(neighbour,true);
                    //mark neighbours parent
                    parent.put(neighbour,front);
                }else if (neighbour != parent.get(front)){
                    // if neighbour is visited & neighbour is not node's parent
                    return true;
                }
            }
        }
        return false;
    }


    private boolean detectCycleInUnDirectedGraphUsingDFS(int[][]edges){
        Map<Integer, List<Integer>> adjacencyList = prepareAdjacencyListForGraph(edges, true);
        Map<Integer,Boolean> visited = new HashMap<>();

        for(Map.Entry<Integer,List<Integer>> source :  adjacencyList.entrySet()){
            if(!visited.containsKey(source.getKey())){
                int parent = -1;
                boolean isCyclic = detectCycleUnDirectedGraphUsingDFS(adjacencyList,source.getKey(), visited, parent);
                if(isCyclic)
                    return true;
                //else check for other components
            }
        }
        return false;
    }

    private boolean detectCycleUnDirectedGraphUsingDFS(Map<Integer, List<Integer>> adjacencyList, int node, Map<Integer,Boolean> visited, int parent ){
        visited.put(node, true);

        for(int neighbour : adjacencyList.get(node)){
            if(!visited.containsKey(neighbour)){
                //recursive call for neighbour's neighbours
                boolean isCyclic = detectCycleUnDirectedGraphUsingDFS(adjacencyList,neighbour, visited, node);
                //if any of sub-component of graph is cyclic
                if(isCyclic){
                    return  true;
                }
            }
            // if neighbour is visited & neighbour is not node's parent
            else if(neighbour != parent)
                    return true;
        }
        return  false;
    }


    private static  boolean cycleDetectionInDirectedGraphUsingDFS(int n, ArrayList < ArrayList < Integer >> edges){
        Map<Integer, List<Integer>> adjacencyList = prepareAdjacencyListForGraph(n,edges);
        Map<Integer,Boolean> visited = new HashMap<>();
        Map<Integer,Boolean> dfsTrack = new HashMap<>();

        for(int i = 1 ; i<= n ;i++){
            int node = i;
            if(!visited.containsKey(node)){
                boolean isCyclic = detectCycleDirectedGraphUsingDFS(adjacencyList,node, visited, dfsTrack);
                if(isCyclic)
                    return true;
                //else check for other components
            }
        }
        return false;
    }

    private static boolean detectCycleDirectedGraphUsingDFS(Map<Integer, List<Integer>> adjacencyList, int node, Map<Integer,Boolean> visited,  Map<Integer,Boolean> dfsTrack){
        //mark node visited
        visited.put(node,true);
        // mark that the node is already seen in this circuit
        dfsTrack.put(node,true);

        for(int neighbour : adjacencyList.get(node)){
            if(!visited.containsKey(neighbour)){
                boolean isCyclic =detectCycleDirectedGraphUsingDFS(adjacencyList,neighbour,visited,dfsTrack);
                if(isCyclic)
                    return  true;
            }
            //neighbour is already visited and it was seen already in this circuit
            else if(dfsTrack.containsKey(neighbour))
                return  true;
        }
        //remove node in dfsTrack to mark it un-visited, once all neighbours are visited
        dfsTrack.remove(node);
        return false;
    }

    public static Map<Integer,List<Integer>> prepareAdjacencyListForGraph(int n ,ArrayList < ArrayList < Integer >> edges){
        Map<Integer,List<Integer>> adjancecyList = new HashMap<>();
        for(int j = 0; j< edges.size();j++){
            int U = edges.get(j).get(0), V = edges.get(j).get(1);
            adjancecyList.put(U, adjancecyList.getOrDefault(U, new ArrayList<>()));
            adjancecyList.get(U).add(V);
        }

        // all nodes which are not added defined in edges should have empty list
        for(int i = 0; i < n;i++){
            if(!adjancecyList.containsKey(i))
                adjancecyList.put(i, new ArrayList<>());
        }
        return adjancecyList;
    }

    private static ArrayList<Integer> topologicalSortOfDirectedAcyclicGraphByDFS(ArrayList < ArrayList < Integer >> edges, int vertices){
        Map<Integer,List<Integer>> adjancecyList1 =  prepareAdjacencyListForGraph(vertices,edges);
        Map<Integer, Boolean> visited = new HashMap<>();
        Stack<Integer> topologicalSortStack = new Stack<>();

        for(Integer source: adjancecyList1.keySet()){
            if(!visited.containsKey(source)){
                topologicalSortDFS(adjancecyList1,source, visited,topologicalSortStack);
            }
        }

        ArrayList<Integer> topologicalSort = new ArrayList<>();

        while(!topologicalSortStack.isEmpty())
            topologicalSort.add(topologicalSortStack.pop());

        // for Acyclic Directed Graph, the size of topological sort would be equal to no of vertices, invalid topological sort means cycle exists
        boolean cycle = (vertices != topologicalSortStack.size());
        System.out.println("Cycle Exists ?: "+cycle);
        return topologicalSort;
    }

    private static void topologicalSortDFS(Map<Integer,List<Integer>> adjacencyList, int source, Map<Integer, Boolean> visited, Stack<Integer> topologicalSortStack){
        visited.put(source,true);

        for(int neighbour : adjacencyList.get(source)){
            if(!visited.containsKey(neighbour)){
                topologicalSortDFS(adjacencyList,neighbour,visited,topologicalSortStack);
            }
        }
        //add the node in dfsVisited once all neighbour's calls are finished, which is the reverse order of topolgical sort
        //The way topological sorting is solved is by processing a node after all of its children are processed.
        // Each time a node is processed, it is pushed onto a stack in order to save the final result.
        topologicalSortStack.add(source);
    }


    private static ArrayList<Integer> topologicalSortInDirectedAcyclicGraphBFSKahnsAlogrithm(ArrayList < ArrayList < Integer >> edges, int vertices){
        Map<Integer,List<Integer>> adjacencyList =  prepareAdjacencyListForGraph(vertices,edges);
        ArrayList<Integer> topologicalSort = new ArrayList<>();
        // array mentioning InDegree of edges to each vertex
        int inDegreeArray [] = new int[vertices];
        //for BFS traversal
        Queue<Integer> queue = new LinkedList<>();

        // populate In-degree for each vertex  calculate in degree
        for(Integer source : adjacencyList.keySet()){
            for(Integer neighbour : adjacencyList.get(source)){
                //increase the indegree of every neighbour of current node
                inDegreeArray[neighbour]++;
            }
        }

        // put all nodes with 0 in-degree in queue
        for(int source = 0; source < vertices; source++){
            // push all nodes with 0 in-degrre in queue
           if(inDegreeArray[source] == 0){
               //push to queue
               queue.offer(source);
           }
        }
        int count = 0;
        //BFS traversal
        while(!queue.isEmpty()){
            int front = queue.poll();
            //add to topological sort
            topologicalSort.add(front);
            count++;
            //process neighbours
            for(int neighbour: adjacencyList.get(front)){
                //reduce the in-degree of each neighbour as one of their source is already visited
                inDegreeArray[neighbour]--;
                //put neighbour if its in-degree is 0
                if(inDegreeArray[neighbour] == 0){
                    //put neighbour in queue
                    queue.offer(neighbour);
                }
            }
        }

        // for Acyclic Directed Graph, the size of topological sort would be equal to no of vertices, invalid topological sort means cycle exists
        boolean cycle = (vertices != topologicalSort.size());
        // or boolean cycle = (vertices != count);
        System.out.println("Cycle Exists ?: "+cycle);
       return topologicalSort;
    }




    public static void main(String[] args) {
        Graph graph = new Graph();
        int[][] edges = {{0,1},{1,2},{2,0}};
        //Map<Integer,List<Integer>> adjancecyList1 =  graph.prepareAdjacencyListForGraph(edges, true);
       // System.out.println("BFS: "+ graph.BFSTraversal(edges));
       // System.out.println("DFS: "+ graph.DFSTraversal(edges));

        int [][] edges2 =  {{0,1},{0,2},{3,5},{5,4},{4,3}};
       // System.out.println("BFS: "+ graph.BFSTraversal(edges2));
        //System.out.println("DFS: "+ graph.DFSTraversal(edges2));

        int [][] edges3=  {{1,2},{2,4},{4,5},{5,3},{3,1}};
       // System.out.println("BFS: "+ graph.BFSTraversal(edges3));
       // System.out.println("DFS: "+ graph.DFSTraversal(edges3));

        ArrayList<ArrayList<Integer>> edges4 = new ArrayList<>();
        edges4.add(new ArrayList<>(Arrays.asList(0,1)));
        edges4.add(new ArrayList<>(Arrays.asList(3,4)));
        edges4.add(new ArrayList<>(Arrays.asList(4,8)));
        edges4.add(new ArrayList<>(Arrays.asList(4,9)));
        edges4.add(new ArrayList<>(Arrays.asList(4,2)));
        edges4.add(new ArrayList<>(Arrays.asList(4,0)));
        edges4.add(new ArrayList<>(Arrays.asList(5,6)));

       // System.out.println("Cycle: "+ cycleDetectionInDirectedGraphUsingDFS(10,edges4));
       System.out.println(topologicalSortOfDirectedAcyclicGraphByDFS(edges4, 10));
        System.out.println(topologicalSortInDirectedAcyclicGraphBFSKahnsAlogrithm(edges4, 10));
        //Map<Integer,List<Integer>> adjancecyList2 =  graph.prepareAdjacencyListForGraph(edges2, true);
    }

    private static void printArr(int [] arr){
        for(int el: arr){
            System.out.print(el+" ");
        }
        System.out.println();
    }
}
