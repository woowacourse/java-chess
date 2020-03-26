package chess.domain.Piece.state;

import chess.domain.Position;

public abstract class Dead extends Moved {
    protected Dead(Position position) {
        super(position);
    }
}
