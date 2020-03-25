package chess.domain.piece;

import java.util.Collections;

public class King extends FixedPiece {
	public King(String name, Color color) {
		super(name, color, Collections.emptyList());
	}
}
