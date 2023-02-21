package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Error;
import es.usantatecla.tictactoe_v2.main.models.Player;

class PlayerView {

	public BoundedCoordinate getPutBoundedCoordinate(Player player) {
		Message.TURN.writeln(player.getColor().name());
		BoundedCoordinate boundedCoordinate;
		Error error;
		do {
			boundedCoordinate = this.getCoordinate(Message.ENTER_COORDINATE_TO_PUT);
			error = player.getPutTokenError(boundedCoordinate);
			error.toString();
		} while (!error.isNull());
		return boundedCoordinate;
	}

	public BoundedCoordinate[] getMoveBoundedCoordinates(Player player) {
		BoundedCoordinate[] boundedCoordinates = new BoundedCoordinate[2];// todo maguc number
		Message.TURN.writeln(player.getColor().name());
		BoundedCoordinate origin;
		Error error;
		do {
			origin = this.getCoordinate(Message.COORDINATE_TO_REMOVE);
			error = player.getOriginMoveTokenError(origin);
			error.toString();
		} while (error != Error.NULL);
		BoundedCoordinate target;
		do {
			target = this.getCoordinate(Message.COORDINATE_TO_MOVE);
			error = player.getTargetMoveTokenError(origin, target);
			error.toString();
		} while (error != Error.NULL);
		boundedCoordinates[0] = origin;
		boundedCoordinates[1] = target;
		return boundedCoordinates;
	}

	private BoundedCoordinate getCoordinate(Message message) {
		assert message != null;

		BoundedCoordinate boundedCoordinate = new BoundedCoordinate();
		boundedCoordinate.read(message.toString());
		return boundedCoordinate;
	}

	public void writeWinner(Player player) {
		Message.PLAYER_WIN.writeln(player.getColor().name());
	}
}
