package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class Rook extends Piece {
	private static final int ROOK_SCORE = 5;

	public Rook(Color color) {
		super(color, "r", new StretchMovingStrategy(Arrays.asList(
			UP, DOWN, LEFT, RIGHT)),
			ROOK_SCORE);
	}
}
