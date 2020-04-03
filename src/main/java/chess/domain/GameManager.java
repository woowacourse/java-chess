package chess.domain;

import chess.domain.board.Position;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

public class GameManager {
	private Pieces pieces;
	private Color currentColor;

	public GameManager(Pieces pieces) {
		this.pieces = pieces;
		this.currentColor = Color.WHITE;
	}

	public void moveFromTo(Position sourcePosition, Position targetPosition) {
		validateEmptySourcePosition(sourcePosition);
		validateOtherPieceSourcePosition(sourcePosition);
		validateSameColorTargetPosition(targetPosition);

		movePiece(sourcePosition, targetPosition);

		nextTurn();
	}

	private void movePiece(Position sourcePosition, Position targetPosition) {
		Path path = pieces.movablePositions(sourcePosition);
		validateNotMovablePosition(targetPosition, path);

		pieces.move(sourcePosition, targetPosition);
	}

	private void nextTurn() {
		this.currentColor = currentColor.reverse();
	}

	private void validateEmptySourcePosition(Position sourcePosition) {
		if (pieces.isBlank(sourcePosition)) {
			throw new IllegalArgumentException("source에 빈 칸을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	private void validateOtherPieceSourcePosition(Position sourcePosition) {
		if (!pieces.isSameColor(sourcePosition, currentColor)) {
			throw new IllegalArgumentException("source에 상대방의 말을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	private void validateSameColorTargetPosition(Position targetPosition) {
		if (pieces.isSameColor(targetPosition, currentColor)) {
			throw new IllegalArgumentException("target에 자신의 말이 있습니다!");
		}
	}

	private void validateNotMovablePosition(Position target, Path path) {
		if (!path.isMovable(target)) {
			throw new IllegalArgumentException("target에 갈 수 없는 곳을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	public boolean isKingDead() {
		return pieces.isKingDead(currentColor);
	}

	public Piece getPiece(String position) {
		return pieces.getPieceByPosition(Position.of(position));
	}

	public Piece getPiece(Position position) {
		return pieces.getPieceByPosition(position);
	}
}
