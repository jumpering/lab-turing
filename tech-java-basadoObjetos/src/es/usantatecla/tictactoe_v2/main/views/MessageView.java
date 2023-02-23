package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Message;
import es.usantatecla.tictactoe_v2.utils.Console;

public class MessageView {

	private Message message;

	public MessageView(Message message){
		this.message = message;
	}

    public void write() {
		Console.getInstance().write(message.toString());
	}

	public void writeln() {
		Console.getInstance().writeln(message.toString());
	}

	public void writeln(String string) {
		assert string != null;
		assert message == Message.PLAYER_WIN || message == Message.TURN;

		String parameter = message == Message.PLAYER_WIN ? Message.$PLAYER : Message.$COLOR;
		Console.getInstance().writeln(message.toString().replaceAll(parameter, string));
	}
}
