package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class Queen extends Piece {
	private static final int QUEEN_SCORE = 9;

	public Queen(Color color) {
		super(color, "q", new StretchMovingStrategy(Arrays.asList(
			UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
			QUEEN_SCORE);
	}
}
