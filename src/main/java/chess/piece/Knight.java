package chess.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.position.Position;
import chess.validator.KnightMoveValidator;

public class Knight extends Piece {
	public Knight(Team team) {
		super(team, "N", new KnightMoveValidator());
	}

//	@Override
//	public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
//		return Collections.emptyList();
//	}
}
