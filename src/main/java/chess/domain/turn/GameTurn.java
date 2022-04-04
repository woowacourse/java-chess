package chess.domain.turn;

import chess.domain.Color;
import chess.domain.Position;

public interface GameTurn {

    GameTurn nextTurn();

    void movePiece(Position source, Position target);

    Color color();

    boolean isEnd();
}
