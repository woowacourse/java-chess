package chess.domain.piece.queen;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Queen extends Piece {
	public Queen(Position position, Team team) {
		super(position, team, new QueenMoveStrategy());
	}
}
