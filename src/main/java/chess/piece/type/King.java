package chess.piece.type;

import chess.piece.type.movable.KingPieceMovable;
import chess.score.Score;
import chess.team.Team;

public class King extends Piece {
	private static final char NAME = 'k';
	private static final Score SCORE = new Score(0);

	public King(Team team) {
		super(NAME, SCORE, team, new KingPieceMovable());
	}
}
