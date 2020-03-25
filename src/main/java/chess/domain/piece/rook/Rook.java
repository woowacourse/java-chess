package chess.domain.piece.rook;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Rook extends Piece {
	public Rook(Position position, Team team) {
		super(position, team, new RookMoveStrategy());
	}
}
