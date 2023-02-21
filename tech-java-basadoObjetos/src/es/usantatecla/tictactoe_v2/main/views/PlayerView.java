package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Error;
import es.usantatecla.tictactoe_v2.main.models.Player;

class PlayerView {

	private Player player;

	public PlayerView(Player player) {
		this.player = player;
	}

	public void play() {
		if (this.player.isComplete()) {
			this.putToken();
		} else {
			this.moveToken();
		}
	}

	private void putToken() {
		Message.TURN.writeln(this.player.getColor().name());
		BoundedCoordinate boundedCoordinate;
		Error error;
		do {
			boundedCoordinate = this.getCoordinate(Message.ENTER_COORDINATE_TO_PUT);
			error = this.player.getPutTokenError(boundedCoordinate);
			error.toString();
		} while (!error.isNull());
		this.player.putToken(boundedCoordinate);
	}

	private BoundedCoordinate getCoordinate(Message message) {
		assert message != null;

		BoundedCoordinate boundedCoordinate = new BoundedCoordinate();
		boundedCoordinate.read(message.toString());
		return boundedCoordinate;
	}

	private void moveToken() {
		Message.TURN.writeln(this.player.getColor().name());
		BoundedCoordinate origin;
		Error error;
		do {
			origin = this.getCoordinate(Message.COORDINATE_TO_REMOVE);
			error = this.player.getOriginMoveTokenError(origin);
			error.toString();
		} while (error != Error.NULL);
		BoundedCoordinate target;
		do {
			target = this.getCoordinate(Message.COORDINATE_TO_MOVE);
			error = this.player.getTargetMoveTokenError(origin, target);
			error.toString();
		} while (error != Error.NULL);
		this.player.moveToken(origin, target);
	}

	public void writeWinner() {
		Message.PLAYER_WIN.writeln(this.player.getColor().name());
	}

}
