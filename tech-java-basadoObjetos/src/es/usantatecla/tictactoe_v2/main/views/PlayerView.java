package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.companyManagement2.utils.Console;
import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Error;
import es.usantatecla.tictactoe_v2.main.models.Player;
import es.usantatecla.tictactoe_v2.utils.BoundedIntDialogView;
class PlayerView {

	public BoundedCoordinate getPutBoundedCoordinate(Player player) {
		new MessageView(Message.TURN).writeln(player.getName());
		BoundedCoordinate boundedCoordinate;
		Error error;
		do {
			boundedCoordinate = this.getCoordinate(Message.ENTER_COORDINATE_TO_PUT);
			error = player.getPutTokenError(boundedCoordinate);
			error.writeln();
		} while (!error.isNull());
		return boundedCoordinate;
	}

	public BoundedCoordinate[] getMoveBoundedCoordinates(Player player) {
		new MessageView(Message.TURN).writeln(player.getName());
		BoundedCoordinate origin;
		Error error;
		do {
			origin = this.getCoordinate(Message.COORDINATE_TO_REMOVE);
			error = player.getOriginMoveTokenError(origin);
			error.writeln();
		} while (error != Error.NULL);
		BoundedCoordinate target;
		do {
			target = this.getCoordinate(Message.COORDINATE_TO_MOVE);
			error = player.getTargetMoveTokenError(origin, target);
			error.writeln();
		} while (error != Error.NULL);
		BoundedCoordinate[] boundedCoordinates = new BoundedCoordinate[]{origin,target};
		return boundedCoordinates;
	}

	private BoundedCoordinate getCoordinate(Message message) {
		assert message != null;

		new Console().writeln(message.toString());
		BoundedIntDialogView boundedIntDialog = new BoundedIntDialogView(1, BoundedCoordinate.getDimension());
		int row = boundedIntDialog.read(Message.ROW.toString()) - 1;
		int column = boundedIntDialog.read(Message.COLUMN.toString()) - 1;
		BoundedCoordinate boundedCoordinate = new BoundedCoordinate();
		boundedCoordinate.setRow(row);
		boundedCoordinate.setColumn(column);
		return boundedCoordinate;
	}
}