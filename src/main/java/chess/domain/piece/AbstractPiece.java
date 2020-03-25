package chess.domain.piece;

import java.util.Set;

import chess.domain.Board;
import chess.domain.position.Position;

public abstract class AbstractPiece implements Piece {
	protected String name;
	protected Color color;

	protected AbstractPiece(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	@Override
	public abstract Set<Position> canMove(Position currentPosition, Board board);

	@Override
	public boolean isEnemy(Piece that) {
		return !this.color.equals(that.getColor());
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public String getName() {
		return name;
	}
}
