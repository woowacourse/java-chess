package chess.domain.game;

import chess.domain.piece.Position;

public interface State {
    void move(Position source, Position target);

    void start();

    void end();

    boolean isFinished();

    State nextTurn();

    String getValue();
}
