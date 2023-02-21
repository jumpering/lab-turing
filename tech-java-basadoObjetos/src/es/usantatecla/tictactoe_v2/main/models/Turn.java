package es.usantatecla.tictactoe_v2.main.models;

public class Turn {

	private Board board;
	private static final int NUMBER_PLAYERS = 2;
	private Player[] players;
	private int activePlayer;

	public Turn(Board board) {
		assert board != null;

		this.board = board;
		this.players = new Player[Turn.NUMBER_PLAYERS];
		this.reset();
	}

	public void reset() {
		for (int i = 0; i < NUMBER_PLAYERS; i++) {
			this.players[i] = new Player(Color.get(i), this.board);
		}
		this.activePlayer = 0;
	}

	public Color getActiveColor() {
		return this.players[this.activePlayer].getColor();
	}

	public Player getActivePlayer() {
		return this.players[activePlayer];
	}

	public void setNextPlayer() {
		if (!this.board.isTicTacToe(this.getActiveColor())) {
			this.activePlayer = (this.activePlayer + 1) % Turn.NUMBER_PLAYERS;
		}
	}

	public boolean isComplete(){
		return this.players[this.activePlayer].isComplete();
	}

	public void putToken(BoundedCoordinate boundedCoordinate){
        this.players[this.activePlayer].putToken(boundedCoordinate);
    }

	public void moveToken(BoundedCoordinate[] boundedCoordinates){
		this.players[this.activePlayer].moveToken(boundedCoordinates[0], boundedCoordinates[1]);
	}
}
