package chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn;

import java.util.ArrayList;
import java.util.List;

import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.NonLeapableChessPiece;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public abstract class Pawn extends NonLeapableChessPiece {

	public static final String NAME = "P";
	private static final int SCORE = 1;

	protected final List<MoveDirection> catchableDirections = new ArrayList<>();

	protected Pawn(final PieceColor pieceColor, final PieceState pieceState) {
		super(pieceColor, pieceState);
	}

	@Override
	protected boolean canMoveRange(final Position sourcePosition, final Position targetPosition) {
		int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return (chessFileGap == 0) && (chessRankGap <= this.pieceState.getPawnMovableRange());
	}

	@Override
	protected boolean isCatchable(final Position sourcePosition, final Position targetPosition) {
		return canCatchDirection(sourcePosition, targetPosition) && canCatchRange(sourcePosition, targetPosition);
	}

	private boolean canCatchDirection(Position sourcePosition, Position targetPosition) {
		return catchableDirections.stream()
			.anyMatch(catchableDirections -> catchableDirections.isSameDirectionFrom(sourcePosition, targetPosition));
	}

	private boolean canCatchRange(Position sourcePosition, Position targetPosition) {
		int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return (chessFileGap == 1) && (chessRankGap == 1);
	}

	@Override
	public String getName() {
		return pieceColor.convertFrom(NAME);
	}

	@Override
	public double getScore() {
		return SCORE;
	}

}
