package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class Bishop extends Piece {
	private static final int BISHOP_SCORE = 3;

	public Bishop(Color color) {
		super(color, "b", new StretchMovingStrategy(Arrays.asList(
			LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN)),
			BISHOP_SCORE);
	}
}
