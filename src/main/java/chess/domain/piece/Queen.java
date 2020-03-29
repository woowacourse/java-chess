package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class Queen extends StretchPiece {
	private static final double QUEEN_SCORE = 9;

	public Queen(String name, Color color) {
		super(name, color, Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN), QUEEN_SCORE);
	}
}
