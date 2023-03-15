package chess.domain.piece;

import chess.domain.RelativePosition;

public interface Piece {

	boolean isMobile(RelativePosition relativePosition);
	boolean isBlack();
	boolean isWhite();
}
