package es.usantatecla.test.tictactoe_v2.main.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.usantatecla.tictactoe_v2.main.models.Board;
import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Color;

public class BoardTest {
    
    private final Board board = new Board();
    private BoundedCoordinate[] boundedCoordinates;

    @BeforeEach
    void init(){
        this.board.reset();
        int size = 9;
        int index = 0;
        this.boundedCoordinates = new BoundedCoordinate[size];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.boundedCoordinates[index] = new BoundedCoordinate(i, j);
                index++;
            }
        }
    }

    @Test
    public void givenBoardWhenIsEmptyThenAllBoundedCoordinatesIsColorNull(){
        this.board.reset();
        for (BoundedCoordinate boundedCoordinate : this.boundedCoordinates) {
            assertEquals(this.board.getColor(boundedCoordinate), Color.NULL);
        }
    }

    @Test
    public void givenBoardWhenIsEmptyLess1x1ThenAllBoundedCoordinatesIsColorNullLess1x1(){
        this.board.reset();
        this.boundedCoordinates[0] = new BoundedCoordinate(0,0);
        this.board.putToken(this.boundedCoordinates[0], Color.X);
        for (BoundedCoordinate boundedCoordinate : this.boundedCoordinates) {
            if(!this.board.isOccupied( new BoundedCoordinate(0,0), Color.X )){
                assertEquals(this.board.getColor(boundedCoordinate), Color.NULL);
            }
        }
    }

    @Test
    public void givenBoardWhenThreeSameColorInLineThenIsTicTacToe(){
        this.board.reset();
        this.boundedCoordinates[0] = new BoundedCoordinate(0,0);
        this.boundedCoordinates[1] = new BoundedCoordinate(0,1);
        this.boundedCoordinates[2] = new BoundedCoordinate(0,2);

        this.board.putToken(this.boundedCoordinates[0], Color.X);
        this.board.putToken(this.boundedCoordinates[1], Color.X);
        this.board.putToken(this.boundedCoordinates[2], Color.X);
        assertTrue(this.board.isTicTacToe(Color.X));
    }

    @Test
    public void givenBoardWhenTwoSameColorInLineThenIsNotTicTacToe(){
        this.board.reset();
        this.boundedCoordinates[0] = new BoundedCoordinate(0,0);
        this.boundedCoordinates[1] = new BoundedCoordinate(0,1);
        this.boundedCoordinates[2] = new BoundedCoordinate(0,2);

        this.board.putToken(this.boundedCoordinates[0], Color.X);
        this.board.putToken(this.boundedCoordinates[1], Color.X);
        this.board.putToken(this.boundedCoordinates[2], Color.O);
        assertFalse(this.board.isTicTacToe(Color.X));
    }
}
