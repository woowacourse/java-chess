package chess.observer;

import chess.piece.Piece;

public interface Publishable {
    void subscribe(Observable observable);

    void push(Piece piece);
}
