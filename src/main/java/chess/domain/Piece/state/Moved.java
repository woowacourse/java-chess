package chess.domain.Piece.state;

import chess.domain.Position;

public abstract class Moved extends Initialized {
    protected Moved(Position position) {
        super(position);
    }
}
