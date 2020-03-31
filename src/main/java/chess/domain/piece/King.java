package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class King extends Piece {
	private static final int KING_SCORE = 0;

	public King(Color color) {
		super(color, "k", new FixedMovingStrategy(Arrays.asList(
			UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
			KING_SCORE);
	}
}
