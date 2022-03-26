package chess.domain.command;

import chess.domain.Position;

public class End implements Command {

	@Override
	public boolean isStart() {
		return false;
	}

	@Override
	public boolean isEnd() {
		return true;
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
