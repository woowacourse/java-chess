package chess.domain.piece.bishop;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Bishop extends Piece {
	public Bishop(Position position, Team team) {
		super(position, team, new BishopMoveStrategy());
	}
}
