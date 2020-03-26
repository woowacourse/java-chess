package chess.domain.piece;

import static chess.domain.piece.PieceScore.*;
import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class King extends FixedPiece {
	public King(String name, Color color) {
		super(name, color, Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN), KING_SCORE);
	}
}
