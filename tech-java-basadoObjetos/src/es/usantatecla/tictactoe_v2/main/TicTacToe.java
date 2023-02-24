package es.usantatecla.tictactoe_v2.main;

import es.usantatecla.tictactoe_v2.main.models.Board;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.Turn;
import es.usantatecla.tictactoe_v2.main.views.BoardView;
import es.usantatecla.tictactoe_v2.main.views.TurnView;
import es.usantatecla.tictactoe_v2.utils.YesNoDialogView;

class TicTacToe {

	private Board board;
	private Turn turn;
	private BoardView boardView;
	private TurnView turnView;

	private TicTacToe() {
		this.board = new Board();
		this.turn = new Turn(this.board);
		this.boardView = new BoardView(this.board);
		this.turnView = new TurnView(this.turn);
	}

	private void play() {
		do {
			this.playGame();
		} while (this.isResumedGame());
	}

	private void playGame() {
		this.board.reset();
		this.turn.reset();
		do {
			this.boardView.write();
			if (!this.turn.isComplete()) {
				this.turn.putToken(this.turnView.getBoundedCoordinateToPut());
			} else {
				this.turn.moveToken(this.turnView.getBoundedCoordinatesToMove());
			}
			this.turn.setNextPlayer();
		} while (!this.board.isTicTacToe(this.turn.getActiveColor()));
		this.boardView.write();
		this.turnView.writeWinner();
	}

	public boolean isResumedGame() {
		YesNoDialogView yesNoDialog = new YesNoDialogView();
		yesNoDialog.read(Message.RESUME.toString());
		return yesNoDialog.isAffirmative();
	}

	public static void main(String[] args) {
		new TicTacToe().play();
	}
}