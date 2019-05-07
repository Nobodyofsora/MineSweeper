package MineSweeper;
import java.util.Scanner;
public class MainFlower {
    public static void main(String[] args) {
        int x,y;
        Scanner input=new Scanner(System.in);
        try {
            int[][] bomb;
            System.out.println("\t Prato fiorito \nQuante bombe vuoi mettere?");
            int numberOfBombs = input.nextInt();
            if (numberOfBombs<0) throw new Exception("Invalid number");
            bomb = new int[numberOfBombs][2];
            for (int i=0; i<numberOfBombs;i++){
                System.out.println("Inserisci coordinate x della "+(i+1)+"° bomba (1-9):");
                x = input.nextInt();
                x--;
                System.out.println("Inserisci coordinate y della "+(i+1)+"° bomba (1-9):");
                y = input.nextInt();
                y--;
                bomb[i][0]=x;
                bomb[i][1]=y;
            }
            int[][] grid = new int[9][9];
            Grid game = new Grid(grid, bomb);
            game.addNumbers();
            System.out.println(game.printPlayerGrid());
            do {
                System.out.println("Dove vuoi colpire?\nCoordinate x(1-9):");
                x=input.nextInt();
                x--;
                System.out.println("Coordinate y(1-9):");
                y=input.nextInt();
                y--;
                if(game.clickAt(x, y)==-1) {
                    System.out.println("Hai perso");
                    break;
                }
            } while (game.checkWin());
            System.out.println(game.printPlayerGrid());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
