package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class Bishop extends StretchPiece {
	private static final double BISHOP_SCORE = 3;

	public Bishop(String name, Color color) {
		super(name, color, Arrays.asList(LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN), BISHOP_SCORE);
	}
}
