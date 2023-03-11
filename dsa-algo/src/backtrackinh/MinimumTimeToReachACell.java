package backtrackinh;

public class MinimumTimeToReachACell {

    public static void main(String[] args) {
        MinimumTimeToReachACell sol = new MinimumTimeToReachACell();
        int[][] grid = {{0,1,3,2},{5,1,2,5},{4,3,8,6}};
        System.out.println("Min Time: "+ sol.minimumTime(grid));
    }

    private int[][] directions = {
            // left, right
            {-1,0},{1,0},
            //up, Down
            {0,-1},{0,1}
    };
    public int minimumTime(int[][] grid) {
        //https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/description/
        //TODO: FIX This IMPL using MinHeap or Queue based approach for BFS type Solution
        if(grid[0][1] > 1 && grid[1][0] > 1)
            return -1;

        boolean[][] visited = new boolean [grid.length][grid.length];
        return timeV1(grid, visited, 0,0, 0);
    }
    private int timeV1(int[][] grid, boolean [][] visited, int row, int col, int time){
        if(row == grid.length-1 && col == grid.length-1){
            return time;
        }

        if(visited[row] [col]){
            return Integer.MAX_VALUE;
        }
        visited[row][col] = true;

        int minTime = Integer.MAX_VALUE;

        for(int indx = 0; indx< directions.length;indx++){
            int newRow = row+ directions[indx][0];
            int newCol = col+ directions[indx][1];
            int currSolvTime = Integer.MAX_VALUE;

            if(invalidMove(grid,visited,newRow,newCol)){
               continue;
            }
            int newTime = time+1;

            //curr cell is unreachable in t+1 time, wait needed
            if(grid[newRow][newCol] > time+1){
                int waitTime = grid[newRow][newCol] - time;
                newTime = (waitTime % 2 == 1)? grid[newRow][newCol] : grid[newRow][newCol]+1;
            }
            currSolvTime = timeV1(grid,visited,newRow,newCol,newTime);

            minTime = Math.min(minTime,currSolvTime);
        }

        //visited[row][col]= false;
        return minTime;
    }

    private int time(int[][] grid, boolean [][] visited, int row, int col, int time){
        System.out.println("At cell["+row+", "+col+"] on time: "+time);
        if(row == grid.length-1 && col == grid.length-1 ){
            return time;
        }

        if(visited[row][col]){
            return  Integer.MAX_VALUE;
        }
        visited[row][col] = true;

        // left
        int newRow = row;
        int newCol = col-1;
        int left  = Integer.MAX_VALUE;
        int minTime = Integer.MAX_VALUE;

        if(canVisit(grid,visited, newRow, newCol,time+1) ){
            System.out.println("Going Left <--- to "+newRow+", "+newCol);
            left = time(grid, visited, newRow, newCol, time+1);
            System.out.println("left: "+left);
            minTime = Math.min(minTime, left);
        }

        // right
        newRow = row;
        newCol = col+1;
        int right  = Integer.MAX_VALUE;

        if(canVisit(grid,visited, newRow, newCol,time+1) ){
            System.out.println("Going Right ---> to "+newRow+", "+newCol);
            right = time(grid, visited, newRow, newCol, time+1);
            System.out.println("right: "+right);
            minTime = Math.min(minTime, right);
        }

        // up
        newRow = row-1;
        newCol = col;
        int up  = Integer.MAX_VALUE;

        if(canVisit(grid,visited, newRow, newCol,time+1) ){
            System.out.println("Going Up ^ to "+newRow+", "+newCol);
            up = time(grid, visited, newRow, newCol, time+1);

            System.out.println("up: "+up);
            minTime = Math.min(minTime, up);
        }
        // down
        newRow = row+1;
        newCol = col;
        int down  = Integer.MAX_VALUE;

        if(canVisit(grid,visited, newRow, newCol,time+1) ){
            System.out.println("Going Down V to "+newRow+", "+newCol);
            down = time(grid, visited, newRow, newCol, time+1);

            System.out.println("down: "+down);
            minTime = Math.min(minTime, down);
        }

        visited[row][col] = false;
        return minTime;
    }
    private boolean invalidMove(int[][] grid, boolean[][] visited, int i, int j){
        // i, j are out of range, or already visited
        return(i<0 || i>= grid.length) ||  (j<0 || j>= grid.length) || visited[i][j];

    }

    private boolean canVisit(int[][] grid, boolean[][] visited, int i, int j,int time ){
        // i, j are out of range
        if((i<0 || i>= grid.length) ||  (j<0 || j>= grid.length))
            return false;
        return !visited[i][j] && grid[i][j] <= time;
    }
}
