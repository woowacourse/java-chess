package chess.domain.piece;

import java.util.List;
import java.util.Set;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public abstract class AbstractPiece implements Piece {
	protected String name;
	protected Color color;
	protected List<Direction> movableDirections;
	protected double score;

	protected AbstractPiece(String name, Color color, List<Direction> movableDirections, double score) {
		this.name = name;
		this.color = color;
		this.movableDirections = movableDirections;
		this.score = score;
	}

	@Override
	public abstract Set<Position> findMovablePositions(Position currentPosition, Board board);

	@Override
	public boolean isEnemy(Piece that) {
		return !this.color.equals(that.getColor());
	}

	@Override
	public boolean isSameColor(Color color) {
		return getColor().equals(color);
	}

	@Override
	public boolean isNotSameColor(Color color) {
		return !getColor().equals(color);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getScore() {
		return score;
	}
}
