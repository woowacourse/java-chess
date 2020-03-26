package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;

import chess.domain.Team;

public class Pawn extends ChessPiece {
	boolean isFirstMove = true;

	public Pawn(Team team) {
		super("p", team, 1, Arrays.asList(
			UP,
			LEFT_UP,
			RIGHT_UP,
			DOUBLE_UP
		));
	}

	public boolean isFirstMove() {
		return isFirstMove;
	}

	public void firstMoveComplete() {
		isFirstMove = false;
	}
}
