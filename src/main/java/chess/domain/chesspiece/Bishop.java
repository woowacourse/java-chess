package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;

import chess.domain.Team;

public class Bishop extends ChessPiece {
	public Bishop(Team team) {
		super("b", team, 3, Arrays.asList(
			LEFT_DOWN,
			LEFT_UP,
			RIGHT_DOWN,
			RIGHT_UP
		));
	}
}
