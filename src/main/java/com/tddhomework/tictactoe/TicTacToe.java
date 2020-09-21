package com.tddhomework.tictactoe;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicTacToe {


    private String table[][] = new String[3][3];
    private int moveCount;
    private String lastPlayed ;

    public String play(int x, int y, User user) {
        if(this.table[x][y] != null){
            throw new IllegalArgumentException("It is already played!");
        }
        if(this.moveCount != 0 && this.lastPlayed == user.getSymbol()){
            throw new BeHumbleAndWaitForYourGodDamnTurnException("Dude, chill !");
        }
        this.table[x][y]=user.getSymbol();
        this.lastPlayed = user.getSymbol();
        this.moveCount++;
        return checkGameStatus(user);
    }

    public boolean checkRows(User user){
        String currentSymbol= user.getSymbol();
        for(int i=0; i<3 ; i++){
           if(this.table[i][0] == currentSymbol && this.table[i][1] == currentSymbol && this.table[i][2] == currentSymbol){
               return true;
           }
        }
        return false;
    }

    public boolean checkColumns(User user){
        String currentSymbol= user.getSymbol();
        for(int i=0; i<3 ; i++){
            if(this.table[0][i] == currentSymbol && this.table[1][i] == currentSymbol && this.table[2][i] == currentSymbol){
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonal(User user){
        String currentSymbol= user.getSymbol();

        if(this.table[0][0] == currentSymbol && this.table[1][1] == currentSymbol && this.table[2][2] == currentSymbol){
           return true;
        }
        if(this.table[0][0] == currentSymbol && this.table[1][1] == currentSymbol && this.table[2][0] == currentSymbol){
            return true;
        }

        return false;
    }

    public String checkGameStatus(User user) {
        if (checkRows(user)) {
            return user.getSymbol();
        }
        if (checkColumns(user)) {
            return user.getSymbol();
        }
        if (checkDiagonal(user)) {
            return user.getSymbol();
        }
        if(this.moveCount == 9){
            return "Draw";
        }
        return "ongoing";
    }
}
