package chess.domain.piece;

import java.util.List;
import java.util.Set;

import chess.domain.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public abstract class AbstractPiece implements Piece {
	protected String name;
	protected Color color;
	protected List<Direction> movableDirections;

	protected AbstractPiece(String name, Color color, List<Direction> movableDirections) {
		this.name = name;
		this.color = color;
		this.movableDirections = movableDirections;
	}

	@Override
	public abstract Set<Position> findMovablePositions(Position currentPosition, Board board);

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
