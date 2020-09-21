package com.tddhomework.tictactoe;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


class TictactoeApplicationTests {

    TicTacToe board = new TicTacToe();
    User user1 = new User();
    User user2 = new User();

    @BeforeEach
    public void setup(){
        user1.setSymbol("X");
        user2.setSymbol("0");
    }

    @AfterEach
    public void setup2(){
        board.setTable(new String[3][3]);
        board.setMoveCount(0);
        board.setLastPlayed(null);
    }

    @Test
    public void play_itShouldReturnChangedMatrix(){
        //Arrange
        int x = 1;
        int y = 1;
        //Act
        board.play(x, y, this.user1);
        //Verify
        assertThat(board.getTable()[x][y]).isEqualTo(this.user1.getSymbol());
    }

    @Test
    public void whenAlreadyHasPlayed_itShouldThrowIllegalArgumentException(){
        //Arrange
        int x = 1;
        int y = 1;
        //act
        board.play(x, y, this.user1);
        Throwable throwable = catchThrowable(() -> board.play(x,y,this.user1));
        //verify
        assertThat(board.getTable()[x][y]).isNotNull();
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("It is already played!");
    }

    @Test
    public void checkTable_WhenItsARowWinSituation_ItShouldReturnWinnersSymbol(){

        //Act
        String gameStatus = createRowWinSituationGame();
        //Assert
        assertThat(gameStatus).isEqualTo("X");
    }

    @Test
    public void play_whenUserTriesPlayAgain_ShouldThrowBeHumbleAndWaitForYourGodDamnTurnException(){

        //Act
        board.play(0,0,this.user1);
        Throwable throwable = catchThrowable(() -> board.play(0,1,this.user1));
        //verify
        assertThat(throwable).isInstanceOf(BeHumbleAndWaitForYourGodDamnTurnException.class).hasMessage("Dude, chill !");
    }


    @Test
    public void checkTable_WhenItsAColumnWinSituation_ItShouldReturnWinnersSymbol(){
        //Act
        String gameStatus = createColumnWinSituationGame();
        //Assert
        assertThat(gameStatus).isEqualTo("X");
    }

    @Test
    public void checkTable_WhenItsADiagonalWinSituation_ItShouldReturnWinnersSymbol(){
        //Act
        String gameStatus = createDiagonalWinSituationGame();
        //Assert
        assertThat(gameStatus).isEqualTo("X");
    }

    public String createRowWinSituationGame(){
        TicTacToe board = new TicTacToe();
        User user1 = new User();
        User user2 = new User();
        user1.setSymbol("X");
        user2.setSymbol("O");
        board.play(0,0,user1);
        board.play(2,0,user2);
        board.play(0,1,user1);
        board.play(2,1,user2);
        return board.play(0,2,user1);
    }
    public String createColumnWinSituationGame(){
        TicTacToe board = new TicTacToe();
        User user1 = new User();
        User user2 = new User();
        user1.setSymbol("X");
        user2.setSymbol("O");
        board.play(0,0,user1);
        board.play(1,1,user2);
        board.play(1,0,user1);
        board.play(2,1,user2);
        return board.play(2,0,user1);
    }
    public String createDiagonalWinSituationGame(){
        TicTacToe board = new TicTacToe();
        User user1 = new User();
        User user2 = new User();
        user1.setSymbol("X");
        user2.setSymbol("O");
        board.play(0,0,user1);
        board.play(2,0,user2);
        board.play(1,1,user1);
        board.play(2,1,user2);
        return board.play(2,2,user1);
    }

}
