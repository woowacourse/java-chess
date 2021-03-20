package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class Blank extends Piece {

	public Blank(Color color, Position position) {
		super(color, position);
		this.type = Type.BLANK;
	}

	@Override
	public boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Direction> directions() {
		throw new UnsupportedOperationException();
	}
}
