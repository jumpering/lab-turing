package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Board;
import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.Turn;
import es.usantatecla.tictactoe_v2.utils.BoundedIntDialog;
import es.usantatecla.tictactoe_v2.utils.YesNoDialog;

public class TicTacToeView {

    private BoardView boardView;
    private TurnView turnView;
    private PlayerView playerView;
    private BoundedIntDialog boundedIntDialog;

    public TicTacToeView(Turn turn, Board board) {
        this.boardView = new BoardView(board);
        this.turnView = new TurnView(turn);
        this.boundedIntDialog = new BoundedIntDialog(1, BoundedCoordinate.getDimension());// todo magic number
        this.playerView = new PlayerView(this.boundedIntDialog);
    }

    public void play() {
        this.boardView.write();
        this.turnView.play(this.playerView);
    }

    public boolean isResumedGame() {
        YesNoDialog yesNoDialog = new YesNoDialog();
        yesNoDialog.read(Message.RESUME.toString());
        return yesNoDialog.isAffirmative();
    }

    public void writeWinner() {
        this.boardView.write();
        this.turnView.writeWinner();
    }

}
