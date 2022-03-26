package chess.domain.state;

import chess.domain.command.Command;

public class Finished extends State {

	@Override
	public State proceed(Command command) {
		throw new IllegalStateException();
	}
}
