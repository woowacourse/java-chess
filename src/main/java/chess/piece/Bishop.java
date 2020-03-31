package chess.piece;

import java.util.List;

import chess.position.Position;
import chess.validator.BishopMoveValidator;

public class Bishop extends Piece {
	public Bishop(Team team) {
		super(team, "B", new BishopMoveValidator());
	}

//	@Override
//	public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
//		return Position.findDiagonalTrace(start, end);
//	}
}
