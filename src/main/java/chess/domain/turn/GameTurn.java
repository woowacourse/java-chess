package chess.domain.turn;

import chess.domain.Color;

public interface GameTurn {

    GameTurn nextTurn();

    Color color();

    boolean isEnd();
}
