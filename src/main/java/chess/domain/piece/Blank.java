package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class Blank extends Piece {
	public static final String BLANK_MOVE_ERROR = "공백은 움직일 수 없습니다.";

	public Blank(Color color, Position position) {
		super(color, position);
		this.type = Type.BLANK;
	}

	@Override
	public void move(ChessBoard chessBoard, Direction direction, Position targetPosition) {
		throw new UnsupportedOperationException(BLANK_MOVE_ERROR);
	}

	@Override
	public boolean isMovable(final ChessBoard chessBoard, final Direction direction, final Position targetPosition) {
		throw new UnsupportedOperationException(BLANK_MOVE_ERROR);
	}

	@Override
	public List<Direction> directions() {
		throw new UnsupportedOperationException(BLANK_MOVE_ERROR);
	}
}
