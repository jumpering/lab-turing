package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Board;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.Turn;
import es.usantatecla.tictactoe_v2.utils.YesNoDialog;

public class TicTacToeView {

    private BoardView boardView;
	private TurnView turnView;
    private PlayerView playerView;

    public TicTacToeView(Turn turn, Board board){
        this.boardView = new BoardView(board);
        this.turnView = new TurnView(turn);
        this.playerView = new PlayerView();
    }

    public void play(){
        this.boardView.write();
        this.turnView.play(this.playerView);
    }

    public boolean isResumedGame() {
		YesNoDialog yesNoDialog = new YesNoDialog();
		yesNoDialog.read(Message.RESUME.toString());
		return yesNoDialog.isAffirmative();
	}

    public void writeWinner() {
        this.turnView.writeWinner();
    }
    
}
