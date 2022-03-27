package chess.domain.command;

import chess.domain.position.Position;

public class End implements Command {

	private static final String CANNOT_HAVE_POSITION = "END 커맨드에선 위치 정보를 불러올 수 없습니다.";

	@Override
	public boolean isStart() {
		return false;
	}

	@Override
	public boolean isMove() {
		return false;
	}

	@Override
	public boolean isStatus() {
		return false;
	}

	@Override
	public Position getFromPosition() {
		throw new IllegalStateException(CANNOT_HAVE_POSITION);
	}

	@Override
	public Position getToPosition() {
		throw new IllegalStateException(CANNOT_HAVE_POSITION);
	}
}
