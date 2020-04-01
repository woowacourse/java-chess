package chess.domain.piece.queen;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Queen extends Piece {
	public static final String WHITE_QUEEN = "\u2655";
	public static final String BLACK_QUEEN = "\u265b";
	public static final int QUEEN_SCORE = 9;

	public String getSymbol() {
		return symbol;
	}

	private final String symbol = "q";

	public Queen(Team team, Position position) {
		super(new QueenStrategy(), team, position);
	}

	public static Queen of(Team team, Position position) {
		return new Queen(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_QUEEN;
		}
		return BLACK_QUEEN;
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
}
