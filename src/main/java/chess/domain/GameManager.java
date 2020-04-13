package chess.domain;

import java.util.List;

import chess.domain.board.Position;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

public class GameManager {
	private Pieces pieces;
	private Color currentColor;

	public GameManager(Pieces pieces, Color currentColor) {
		this.pieces = pieces;
		this.currentColor = currentColor;
	}

	public void moveFromTo(Position sourcePosition, Position targetPosition) {
		validateOtherPieceSourcePosition(sourcePosition);
		validateSameColorTargetPosition(targetPosition);

		movePiece(sourcePosition, targetPosition);

		nextTurn();
	}

	public List<String> getMovablePositions(Position sourcePosition) {
		Path path = pieces.movablePositions(sourcePosition);
		return path.getPositions();
	}

	private void movePiece(Position sourcePosition, Position targetPosition) {
		Path path = pieces.movablePositions(sourcePosition);
		validateNotMovablePosition(targetPosition, path);

		pieces.move(sourcePosition, targetPosition);
	}

	private void nextTurn() {
		this.currentColor = currentColor.reverse();
	}

	private void validateOtherPieceSourcePosition(Position sourcePosition) {
		if (pieces.isSameColor(sourcePosition, currentColor.reverse())) {
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

	public Color getCurrentColor() {
		return currentColor;
	}
}
