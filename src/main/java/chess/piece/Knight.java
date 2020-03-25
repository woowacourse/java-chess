package chess.piece;

import java.util.List;

import chess.position.Position;

public class Knight extends Piece {
	private static final String SYMBOL = "K";

	public Knight(Team team) {
		super(team);
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		return null;
	}

	@Override
	protected String getSymbol() {
		return SYMBOL;
	}
}
