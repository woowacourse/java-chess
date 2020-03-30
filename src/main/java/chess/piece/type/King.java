package chess.piece.type;

import chess.piece.type.movable.KingPieceMovable;
import chess.score.Score;
import chess.team.Team;

public class King extends Piece {
	private static final char NAME = 'k';
	private static final int SCORE = 0;

	public King(Team team) {
		super(NAME, new Score(SCORE), team, new KingPieceMovable());
	}
}
