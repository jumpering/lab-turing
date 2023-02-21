package es.usantatecla.tictactoe_v2.main;

import es.usantatecla.tictactoe_v2.main.models.Board;
import es.usantatecla.tictactoe_v2.main.models.Turn;
import es.usantatecla.tictactoe_v2.main.views.TicTacToeView;
class TicTacToe {

	private Board board;
	private Turn turn;
	private TicTacToeView ticTacToeView;

	private TicTacToe() {
		this.board = new Board();
		this.turn = new Turn(this.board);
		this.ticTacToeView = new TicTacToeView(this.turn, this.board);
	}

	private void play() {
		do {
			this.playGame();
		} while (this.ticTacToeView.isResumedGame());
	}

	private void playGame() {
		this.board.reset();
		this.turn.reset();
		do {
			this.ticTacToeView.play();
		} while (!this.board.isTicTacToe(this.turn.getActiveColor()));
		this.ticTacToeView.writeWinner();
	}

	public static void main(String[] args) {
		new TicTacToe().play();
	}
}