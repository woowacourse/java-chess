package chess.domain.piece.movable;

import chess.domain.position.RelativePosition;

public interface Movable {

    boolean isMobile(RelativePosition relativePosition);

}
