package chess.domain.board;

import java.util.Arrays;
import java.util.function.BiPredicate;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public enum BoardOccupyState {
	EMPTY((source, target) -> target.isBlank()),
	ENEMY_TEAM((source, target) -> !source.isSameTeam(target));

	private static final String NOT_ACCEPTABLE_MOVING_POSITION_EXCEPTION = "해당 위치로 이동할 수 없습니다.";

	private final BiPredicate<Piece, Piece> boardStateChecker;

	BoardOccupyState(BiPredicate<Piece, Piece> boardStateChecker) {
		this.boardStateChecker = boardStateChecker;
	}

	public static BoardOccupyState of(Piece source, Piece target) {
		return Arrays.stream(values())
			.filter(val -> val.boardStateChecker.test(source, target))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("같은 색상의 말은 잡을 수 없습니다."));
	}

	public void checkMovable(Board board, Piece source, Position from, Position to) {
		if (this == EMPTY && board.isExistAnyPieceAt(source.findMoveModeTrace(from, to))) {
			throw new IllegalArgumentException(NOT_ACCEPTABLE_MOVING_POSITION_EXCEPTION);
		}

		if (this == ENEMY_TEAM && board.isExistAnyPieceAt(source.findCatchModeTrace(from, to))) {
			throw new IllegalArgumentException(NOT_ACCEPTABLE_MOVING_POSITION_EXCEPTION);
		}
	}
}
