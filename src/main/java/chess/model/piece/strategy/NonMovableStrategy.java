package chess.model.piece.strategy;

import chess.model.piece.Piece;

public class NonMovableStrategy implements MovableStrategy {
    @Override
    public boolean movable(Piece source, Piece target) {
        return false;
    }
}
