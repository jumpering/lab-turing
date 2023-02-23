package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Board;
import es.usantatecla.tictactoe_v2.main.models.BoundedCoordinate;
import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.utils.Console;
public class BoardView {

	private Board board;
	private MessageView messageView = new MessageView();

	public BoardView(Board board) {
		this.board = board;
		this.messageView.writeln(Message.TITLE);
	}

	public void write() {
		this.messageView.writeln(Message.HORIZONTAL_LINE);
		for (int i = 0; i < BoundedCoordinate.getDimension(); i++) {
			this.messageView.write(Message.VERTICAL_LINE);
			for (int j = 0; j < BoundedCoordinate.getDimension(); j++) {
				new ColorView(this.board.getColor(new BoundedCoordinate(i, j))).write();
				this.messageView.write(Message.VERTICAL_LINE);
			}
			Console.getInstance().writeln();
		}
		this.messageView.writeln(Message.HORIZONTAL_LINE);
	}
}