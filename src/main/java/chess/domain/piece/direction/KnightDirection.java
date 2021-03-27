package chess.domain.piece.direction;

import chess.domain.position.Position;

public abstract class KnightDirection extends Direction{
    protected KnightDirection(int x, int y) {
        super(x, y);
    }
}
