package chess.piece;

import chess.Path;
import chess.Player;
import chess.Position;
import chess.Score;
import chess.pattern.Pattern;
import chess.pattern.RookPattern;

public class Rook implements Piece {
	private static final double ROOK_SCORE = 5;

	private final Player player;
	private final Score score;
	private final Pattern pattern;
	private Position currentPosition;

	public Rook(Player player, Position currentPosition) {
		this.player = player;
		this.currentPosition = currentPosition;
		this.pattern = new RookPattern();
		this.score = new Score(ROOK_SCORE);
	}

	@Override
	public Path move(Position position) {
		return pattern.move(this.currentPosition, position);
	}
}
