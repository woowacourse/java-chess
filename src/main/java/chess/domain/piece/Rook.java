package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public class Rook extends StretchPiece {
	public Rook(String name, Color color) {
		super(name, color, Arrays.asList(UP, DOWN, LEFT, RIGHT));
	}
}
