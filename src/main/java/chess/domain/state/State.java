package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public interface State {

    boolean isSameColor(final Piece piece);

    State nextState();

    boolean isFinish();

    void nextTurn();

    Color winner();

    void validateMove();
}
