package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.Turn;
public class TurnView {

    private Turn turn;
    private PlayerView playerView;

    public TurnView(Turn turn) {
        this.turn = turn;
        this.playerView = new PlayerView();
    }

    public BoundedCoordinate getBoundedCoordinateToPut(){
        BoundedCoordinate boundedCoordinate = this.playerView
            .getBoundedCoordinateToPut(this.turn.getActivePlayer());
        return boundedCoordinate;
    }

    public BoundedCoordinate[] getBoundedCoordinatesToMove(){
        BoundedCoordinate[] boundedCoordinates = this.playerView
            .getBoundedCoordinatesToMove(this.turn.getActivePlayer());
            return boundedCoordinates;
    }

    public void writeWinner() {
        new MessageView(Message.PLAYER_WIN).writeln(this.turn.getActivePlayer().getName());
	}
}