package chess.domain.board;

import chess.domain.position.Position;

public interface Board {

    Board initialize();

    boolean isInitialized();

    boolean isEnd();

    Board move(final Position source, final Position target);

    GameResult getResult();
}
