package chess.domain.piece.bishop;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Bishop extends Piece {
	private static final double BISHOP_SCORE = 3;

	private final String symbol = "b";

	public Bishop(Team team, Position position) {
		super(new BishopStrategy(), team, position);
	}

	public static Piece of(Team team, Position position) {
		return new Bishop(team, position);
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		strategy.validateMove(from, to, dto);
		this.position = to;
		return this;
	}

	@Override
	public double getScore() {
		return BISHOP_SCORE;
	}

	public String getSymbol() {
		return symbol;
	}
}
