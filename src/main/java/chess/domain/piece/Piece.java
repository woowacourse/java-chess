package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece implements Movable {
	public static final String NOT_MOVABLE_POSITION_ERROR = "이동할 수 없는 위치입니다.";

	protected Type type;
	private final Color color;
	private Position position;

	public Piece(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public String getName() {
		return type.nameByColor(color);
	}

	public Color getColor() {
		return color;
	}

	public boolean isNotSameColor(Piece piece) {
		return !this.getColor().equals(piece.getColor());
	}

	public boolean isSameColor(Color color) {
		return this.getColor().equals(color);
	}

	public boolean isBlank() {
		return type.equals(Type.BLANK);
	}

	public boolean isBlack() {
		return color.isBlack();
	}

	public boolean isPawn() {
		return type.equals(Type.PAWN);
	}

	public boolean isKing() {
		return type.equals(Type.KING);
	}

	public double score() {
		return type.getScore();
	}

	public void move(ChessBoard chessBoard, Direction direction, Position targetPosition) {
		if (isMovable(chessBoard, direction, targetPosition)) {
			chessBoard.replace(this.position, new Blank(Color.NO_COLOR, this.position));
			this.position = targetPosition;
			chessBoard.replace(targetPosition, this);
			return;
		}
		throw new IllegalArgumentException(NOT_MOVABLE_POSITION_ERROR);
	}

	public Position nextPosition(Direction direction) {
		return position.nextPosition(direction);
	}

	public abstract List<Direction> directions();

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Piece piece = (Piece) o;
		return color == piece.color && Objects.equals(position, piece.position) && type == piece.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, position, type);
	}
}
