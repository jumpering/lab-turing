package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.Turn;
public class TurnView {

    private Turn turn;

    public TurnView(Turn turn) {
        this.turn = turn;
    }

    public void play(PlayerView playerView) {
        if (!this.turn.isComplete()) {
            BoundedCoordinate boundedCoordinate = playerView
                    .getPutBoundedCoordinate(this.turn.getActivePlayer());
            this.turn.putToken(boundedCoordinate);
        } else {
            BoundedCoordinate[] boundedCoordinates = playerView
                    .getMoveBoundedCoordinates(this.turn.getActivePlayer());
            this.turn.moveToken(boundedCoordinates);
        }
        this.turn.setNextPlayer();
    }

    public void writeWinner() {
        MessageView messageView = new MessageView();
        messageView.writeln(Message.PLAYER_WIN, this.turn.getActivePlayer().getName());
	}
}