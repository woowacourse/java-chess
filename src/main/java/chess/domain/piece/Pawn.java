package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class Pawn extends Piece {
	public Pawn(Color color, Position position) {
		super(color, position);
		this.type = Type.PAWN;
	}

	@Override
	public List<Direction> directions() {
		if (isBlack()) {
			return Direction.blackPawnDirection();
		}
		return Direction.whitePawnDirection();
	}

	@Override
	public boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
		if (direction.isLinear()) {
			return isLinearMovable(chessBoard, direction, targetPosition);
		}
		return isDiagonalMovable(chessBoard, direction, targetPosition);
	}

	private boolean isLinearMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
		Position nextPosition = this.nextPosition(direction);
		Piece nextPiece = chessBoard.getPieceAt(nextPosition);
		if (nextPiece.isBlank() && nextPosition.equals(targetPosition)) {
			return true;
		}
		nextPosition = nextPosition.nextPosition(direction);
		nextPiece = chessBoard.getPieceAt(nextPosition);
		return nextPiece.isBlank() && nextPosition.equals(targetPosition);
	}

	private boolean isDiagonalMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
		Position nextPosition = this.nextPosition(direction);
		Piece nextPiece = chessBoard.getPieceAt(nextPosition);
		return nextPosition.equals(targetPosition) && !nextPiece.isBlank() && this.isNotSameColor(nextPiece);
	}
}
