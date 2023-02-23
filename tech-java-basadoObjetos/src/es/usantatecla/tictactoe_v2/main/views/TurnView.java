package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.Turn;
public class TurnView {

    private Turn turn;

    public TurnView(Turn turn) {
        this.turn = turn;
    }

    public void play() {
        PlayerView playerView = new PlayerView();
        if (!this.turn.isComplete()) {
            BoundedCoordinate boundedCoordinate = playerView
                    .getBoundedCoordinateToPut(this.turn.getActivePlayer());
            this.turn.putToken(boundedCoordinate);
        } else {
            BoundedCoordinate[] boundedCoordinates = playerView
                    .getBoundedCoordinatesToMove(this.turn.getActivePlayer());
            this.turn.moveToken(boundedCoordinates);
        }
        this.turn.setNextPlayer();
    }

    public void writeWinner() {
        new MessageView(Message.PLAYER_WIN).writeln(this.turn.getActivePlayer().getName());
	}
}