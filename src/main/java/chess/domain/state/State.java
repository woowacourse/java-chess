package chess.domain.state;

import chess.domain.pieces.component.Team;

public interface State {
    boolean isEnd();

    void move(Runnable runnable);

    Team getTurn();

    void startGame();
}
