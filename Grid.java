package MineSweeper;

import java.util.Arrays;

public class Grid {
    public int[][] grid;
    public int[][] playerGrid;
    public int[][] myBombs;
    public enum Cell{
        ZERO,
        NUMBER,
        NON_NUMBER;
    }

    public Grid(int[][] grid,int[][] bombs ) {
        this.myBombs= bombs;
        this.grid = grid;
        this.playerGrid = new int[grid.length][grid.length];
        for (int i=0;i<this.grid.length;i++)
            for (int j=0;j<this.grid.length;j++)
                this.playerGrid[i][j]= 0;
    }

    public void addNumbers () throws Exception {
        int x, y;
         for (int[] bomb : this.myBombs) {
            x = bomb[0];
            y = bomb[1];
            if ((x < 0 || x > this.grid.length )&&( y < 0 || y > this.grid.length))
                throw new Exception("Invalid coordinates");
            else {
                if (x + 1 < this.grid.length && y + 1 < this.grid.length)
                    grid[x + 1][y + 1]++;
                if (x - 1 >=0 && y - 1 >=0)
                    grid[x - 1][y - 1]++;
                if (x + 1 < this.grid.length)
                    grid[x + 1][y]++;
                if (y + 1 < this.grid.length)
                    grid[x][y + 1]++;
                if (x - 1 >=0)
                    grid[x - 1][y]++;
                if (y - 1 >=0)
                    grid[x][y - 1]++;
                if (x + 1 < this.grid.length && y - 1 >=0)
                    grid[x + 1][y - 1]++;
                if (x - 1 >=0 && y + 1 < this.grid.length)
                    grid[x - 1][y + 1]++;
            }
        }
        for (int[] myBomb : this.myBombs) {
            x = myBomb[0];
            y = myBomb[1];
            this.grid[x][y] = -1;
        }
    }
    public int clickAt (int x, int y) throws Exception {
        if (x < 0 || x > this.grid.length || y < 0 || y > this.grid.length)
            throw new Exception("Invalid coordinates");
        else {
            if (grid[x][y] == -1) {
                revealGrid(x, y,Cell.NON_NUMBER );
                return grid[x][y];
            } else if (grid[x][y] != 0)
                     revealGrid(x, y,Cell.NUMBER );
            else revealGrid(x, y,Cell.ZERO );
                return grid[x][y];

        }
    }
    public  boolean revealGrid ( int x, int y, Cell number){
        switch (number){
            case NON_NUMBER:
                if(x<0||x>this.grid.length||y<0||y>this.grid.length) return true;
                else {
                    this.playerGrid[x][y] = this.grid[x][y];
                    return revealGrid(x + 1, y + 1, Cell.NON_NUMBER);//TODO da migliorare
                }
            case NUMBER:
                if(x<0||x>this.grid.length||y<0||y>this.grid.length)return true;
                else {
                    this.playerGrid[x][y] = this.grid[x][y];
                    return revealGrid(x + 1, y + 1, Cell.NUMBER);//TODO da migliorare
                }
            case ZERO:

                if(x<0||x>this.grid.length||y<0||y>this.grid.length)return true;
                //TODO da migliorare
                for (int i = 0; i < this.grid.length; i++)
                for (int j = 0; j < this.grid.length; j++) {
                    if (this.grid[x + i][y + j] != -1) this.playerGrid[x + 1][y + j] = this.grid[x + i][y + j];
                    if (this.grid[x - i][y - j] != -1) this.playerGrid[x - 1][y - j] = this.grid[x - i][y - j];
                    if (this.grid[x - i][y + j] != -1) this.playerGrid[x - 1][y + j] = this.grid[x - i][y + j];
                    if (this.grid[x + i][y - j] != -1) this.playerGrid[x + 1][y - j] = this.grid[x + i][y - j];
                }
                return revealGrid(x + 1, y + 1, Cell.ZERO);//TODO da migliorare
        }
        return false;
    }
    public boolean checkWin(){
        for (int j = 0; j < this.grid.length; j++)
            for (int k = 0; k < this.grid.length; k++)
                if (grid[j][k] == 0)
                    return false;
        return true;
    }
    public String printPlayerGrid() {
        String s="";
        for(int i=this.playerGrid.length-1; i>=0; i--) {
            for (int j = 0; j < this.playerGrid.length; j++) {
                if (j==0) s += "|\t";
                else  s+="\t";
                if (playerGrid[j][i] == 0) s += " ";
                else s += playerGrid[j][i];
                s += "\t|";

            }
            s+= "\n";
        }
        return s;
    }
    @Override
    public String toString() {
        String s="";
        for(int i=this.grid.length-1; i>=0; i--) {
            for (int j = 0; j < this.grid.length; j++) {
                if (j==0) s += "|\t"+ grid[j][i] + "\t|";
                s += "\t" + grid[j][i] + "\t|";
            }
        s+= "\n";
        }
        return s;
    }
}
