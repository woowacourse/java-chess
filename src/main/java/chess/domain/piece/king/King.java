package chess.domain.piece.king;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class King extends Piece {
	public King(Position position, Team team) {
		super(position, team, new KingMoveStrategy());
	}
}