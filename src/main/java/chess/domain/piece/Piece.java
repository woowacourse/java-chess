package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;
import java.util.Objects;

public class Piece {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "말을 생성할 수 없습니다.";

	private Position position;
	private final PieceType pieceType;
	private final Color color;

	public Piece(Position position, PieceType pieceType, Color color) {
		this.position = position;
		this.pieceType = pieceType;
		this.color = color;
	}

	public Positions createMovablePositions(List<Piece> pieces) {
		return pieceType.findMovablePositions(position, pieces, color);
	}

	public void move(Position position) {
		if (isSamePosition(position)) {
			throw new UnsupportedOperationException("같은 위치로 이동할 수 없습니다.");
		}
		this.position = position;
	}

	public boolean isSamePosition(Position position) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		return this.position.equals(position);
	}

	public boolean isSameColor(Color color) {
		Objects.requireNonNull(color, INVALID_INPUT_EXCEPTION_MESSAGE);
		return this.color.isSame(color);
	}

	public boolean isNotSameColor(Color color) {
		return !isSameColor(color);
	}

	public boolean isWhite() {
		return color.isWhite();
	}

	public boolean isKing() {
		return pieceType.isKing();
	}

	public boolean isPawn() {
		return pieceType.isPawn();
	}

	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public String getResource() {
		if (color.isWhite()) {
			return pieceType.getResource().toLowerCase();
		}
		return pieceType.getResource();
	}

	public double getScore() {
		return pieceType.getScore();
	}

	public PieceType getPieceType() {
		return pieceType;
	} // TODO: 2020/04/05 제거?
}
