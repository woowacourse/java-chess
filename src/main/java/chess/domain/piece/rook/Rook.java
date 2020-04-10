package chess.domain.piece.rook;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Rook extends Piece {
	private static final int ROOK_SCORE = 5;

	private final String symbol = "r";

	public Rook(Team team, Position position) {
		super(new RookStrategy(), team, position);
	}

	public static Rook of(Team team, Position position) {
		return new Rook(team, position);
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		strategy.validateMove(from, to, dto);
		this.position = to;
		return this;
	}

	@Override
	public double getScore() {
		return ROOK_SCORE;
	}

	public String getSymbol() {
		return symbol;
	}
}

