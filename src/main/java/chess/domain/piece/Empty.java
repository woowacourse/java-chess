package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class Empty extends Piece {

	private static final String NOT_MOVABLE_PIECE_ERROR_MESSAGE = "이동이 불가능한 기물입니다.";

	public Empty() {
		super(Team.EMPTY, Movement.EMPTY);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		throw new IllegalStateException(NOT_MOVABLE_PIECE_ERROR_MESSAGE);
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public PieceType getType() {
		return PieceType.EMPTY;
	}
}
