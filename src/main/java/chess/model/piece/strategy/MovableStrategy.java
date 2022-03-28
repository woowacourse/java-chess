package chess.model.piece.strategy;

import chess.model.piece.Piece;

public interface MovableStrategy {
    boolean movable(Piece source, Piece target);
}
