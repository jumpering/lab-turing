package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Board;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.Turn;
import es.usantatecla.tictactoe_v2.utils.YesNoDialogView;

public class TicTacToeView {

    private BoardView boardView;
    private TurnView turnView;

    public TicTacToeView(Turn turn, Board board) {
        this.boardView = new BoardView(board);
        this.turnView = new TurnView(turn);
    }

    public void play() {
        this.boardView.write();
        this.turnView.play();
    }

    public boolean isResumedGame() {
        YesNoDialogView yesNoDialog = new YesNoDialogView();
        yesNoDialog.read(Message.RESUME.toString());
        return yesNoDialog.isAffirmative();
    }

    public void writeWinner() {
        this.boardView.write();
        this.turnView.writeWinner();
    }

}
