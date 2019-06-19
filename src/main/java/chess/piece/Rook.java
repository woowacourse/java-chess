package chess.piece;

import chess.Player;
import chess.Position;
import chess.Score;
import chess.pattern.RookPattern;

public class Rook extends Piece {
	private static final double ROOK_SCORE = 5;

	private Rook(Player player, Position currentPosition) {
		super(player, new Score(ROOK_SCORE), new RookPattern(), currentPosition);
	}

	public static Rook valueOf(Player player, Position currentPosition) {
		return new Rook(player, currentPosition);
	}
}
