package chess.domain.piece;

import static chess.domain.piece.PieceScore.*;
import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class Knight extends FixedPiece {
	public Knight(String name, Color color) {
		super(name, color, Arrays.asList(
			LEFT_LEFT_DOWN, LEFT_LEFT_UP,
			RIGHT_RIGHT_DOWN, RIGHT_RIGHT_UP,
			LEFT_DOWN_DOWN, LEFT_UP_UP,
			RIGHT_DOWN_DOWN, RIGHT_UP_UP), KNIGHT_SCORE
		);
	}
}
