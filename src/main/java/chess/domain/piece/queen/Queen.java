package chess.domain.piece.queen;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Queen extends Piece {
	private static final int QUEEN_SCORE = 9;

	private final String symbol = "q";

	public Queen(Team team, Position position) {
		super(new QueenStrategy(), team, position);
	}

	public static Queen of(Team team, Position position) {
		return new Queen(team, position);
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		strategy.validateMove(from, to, dto);
		this.position = to;
		return this;
	}

	@Override
	public double getScore() {
		return QUEEN_SCORE;
	}

	public String getSymbol() {
		return symbol;
	}
}
