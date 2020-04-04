package chess.observer;

import chess.piece.Piece;

public interface Observable {
    void update(Piece piece);
}
