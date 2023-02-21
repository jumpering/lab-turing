package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Turn;

public class TurnView {

    private Turn turn;
    private PlayerView playerView;

    public TurnView(Turn turn) {
        this.turn = turn;
        this.playerView = new PlayerView();
    }

    public void play() {
        if (!this.turn.isComplete()) {
            BoundedCoordinate boundedCoordinate = this.playerView
                    .getPutBoundedCoordinate(this.turn.getActivePlayer());
            this.turn.putToken(boundedCoordinate);
        } else {
            BoundedCoordinate[] boundedCoordinates = this.playerView
                    .getMoveBoundedCoordinates(this.turn.getActivePlayer());
            this.turn.moveToken(boundedCoordinates);
        }
        this.turn.setNextPlayer();
    }

    public void writeWinner() {
        this.playerView.writeWinner(this.turn.getActivePlayer());
    }
}
