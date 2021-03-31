package chess.domain.state;

import chess.domain.piece.Color;

public interface State {
    Color color();

    State opposite();

    State end();

    State status();

    boolean isEnd();

    State start();
}
