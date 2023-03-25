package com.test.tictactoe;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class GameProcess {

    public static String currentPlayer="X";
    public static String winningStatePlayerX;
    public static String winningStatePlayerO;
    public static int emptySpace;
    public static void process(String[][] dimension, int boardSize, Scanner scanner){
        //initialize
        emptySpace=boardSize*boardSize;
        boolean winner=false;

        winningStatePlayerX="";
        winningStatePlayerO="";
        initializeBoard(boardSize,dimension);
        do {
            System.out.println("Player "+currentPlayer+" turn!");
            System.out.print("input coordinate x= ");
            int x = scanner.nextInt();
            System.out.print("input coordinate y= ");
            int y = scanner.nextInt();

            if (validateInput(x,y,boardSize))
                inputTurn(x,y,dimension);

            printBoard(boardSize,dimension);

            String checkWin=checkWinner(dimension, boardSize);
            if (!checkWin.equals("")) {
                System.out.println(checkWin);
                winner=true;
            }

        }while (!winner);
    }

    private static void initializeBoard(int input,String[][] dimension) {
        for (int i=0;i<input;i++){
            for (int j=0;j<input;j++){
                dimension[i][j]="-";
            }
            winningStatePlayerX=winningStatePlayerX+"X";
            winningStatePlayerO=winningStatePlayerO+"O";
        }
    }

    private static void printBoard(int input,String[][] dimension){
        for (int i = 0; i < input; i++) {
            System.out.print("||");
            for (int j = 0; j < input; j++) {
                System.out.print(dimension[i][j]+ " ");

            }
            System.out.print("||");
            System.out.println();
        }
    }
    private static boolean validateInput(int x, int y,int input) {
        if (x>input || y>input){
            System.out.println("Input out of Bound!");
            return false;
        }
        return true;

    }

    private static void inputTurn(Integer x, Integer y, String[][] dimension){

        if (dimension[x - 1][y - 1].equals("X")||dimension[x - 1][y - 1].equals("O")){
            System.out.println("The node is already filled!");
        }else {
            dimension[x - 1][y - 1] = currentPlayer;
            emptySpace--;
            currentPlayer = (currentPlayer == "X") ? "O" : "X";
        }
    }


    private static String checkWinner(String[][] dimension, int size){
        String resultRow="";
        String resultColumn="";
        String resultDiagonal1="";
        String resultDiagonal2="";
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (i==j){
                    resultDiagonal1=resultDiagonal1+dimension[i][j];
                }
                if (i+j==size-1){
                    resultDiagonal2=resultDiagonal2+dimension[i][j];
                }
                resultRow=resultRow+dimension[i][j];
                resultColumn=resultColumn+dimension[j][i];
            }
            if (checkWinnerValue(resultColumn,resultRow,winningStatePlayerX)){
                return "Player X wins";
            } else if(checkWinnerValue(resultColumn,resultRow,winningStatePlayerO)){
                return "Player O wins";
            }
            resultColumn="";
            resultRow="";
        }
        if (checkWinnerValue(resultDiagonal1,resultDiagonal2,winningStatePlayerX)){
            return "Player X wins";
        }
        else if(checkWinnerValue(resultDiagonal1,resultDiagonal2,winningStatePlayerO)){
            return "Player O wins";
        }
        if (emptySpace==0)
            return "Tie!";

        return "";
    }
    private static boolean checkWinnerValue(String winState1,String winState2,String player){
        if(winState1.equals(player)||winState2.equals(player))
            return true;
        return false;
    }
}
