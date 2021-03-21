package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;

public interface Movable {
	void move(ChessBoard chessBoard, Direction direction, Position targetPosition);

	boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition);

	Position nextPosition(Direction direction);

	boolean isNotAlly(Piece piece);
}
