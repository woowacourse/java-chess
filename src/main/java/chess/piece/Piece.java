package chess.piece;

import chess.Path;
import chess.Player;
import chess.Position;
import chess.Score;
import chess.pattern.Pattern;

public abstract class Piece {
	private final Player player;
	private final Score score;
	private final Pattern pattern;
	private Position currentPosition;

	Piece(Player player, Score score, Pattern pattern, Position currentPosition) {
		this.player = player;
		this.currentPosition = currentPosition;
		this.pattern = pattern;
		this.score = score;
	}

	public Path move(Position position) {
		return pattern.move(this.currentPosition, position);
	}

	public boolean isSamePosition(Piece piece) {
		return this.currentPosition.equals(piece.currentPosition);
	}

}
