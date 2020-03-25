package chess.domain.piece.knight;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Knight extends Piece {
	public Knight(Position position, Team team) {
		super(position, team, new KnightMoveStrategy());
	}
}
