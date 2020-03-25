package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Pawn extends Piece {
	public Pawn(Position position, Team team) {
		super(position, team, new PawnMoveStrategy(team));
	}
}
