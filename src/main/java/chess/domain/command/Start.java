package chess.domain.command;

import chess.domain.Position;

public class Start implements Command {

	@Override
	public boolean isStart() {
		return true;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public boolean isMove() {
		return false;
	}

	@Override
	public Position getFromPosition() {
		throw new IllegalStateException();
	}

	@Override
	public Position getToPosition() {
		throw new IllegalStateException();
	}
}
