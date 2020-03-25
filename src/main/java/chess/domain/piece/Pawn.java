package chess.domain.piece;

import java.util.Collections;

public class Pawn extends FixedPiece {
	public Pawn(String name, Color color) {
		super(name, color, Collections.emptyList());
	}
}
