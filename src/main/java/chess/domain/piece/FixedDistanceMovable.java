package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;

public interface FixedDistanceMovable extends Movable {
	@Override
	default boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
		Position nextPosition = this.nextPosition(direction);
		Piece targetPiece = chessBoard.getPiece(this.nextPosition(direction));
		return nextPosition.equals(targetPosition) && this.isNotSameColor(targetPiece);
	}
}
