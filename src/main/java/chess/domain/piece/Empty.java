package chess.domain.piece;

import chess.domain.RelativePosition;

public class Empty implements Piece {

	public Empty() {
	}

	@Override
	public boolean isMobile(final RelativePosition relativePosition) {
		throw new IllegalStateException("제공하지 않는 기능입니다.");
	}

	@Override
	public boolean isBlack() {
		throw new IllegalStateException("제공하지 않는 기능입니다.");
	}

	@Override
	public boolean isWhite() {
		throw new IllegalStateException("제공하지 않는 기능입니다.");
	}
}
