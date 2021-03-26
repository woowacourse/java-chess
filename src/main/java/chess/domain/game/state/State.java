package chess.domain.game.state;

import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;

import java.util.List;

public interface State {
    State start();

    void moveIfValidColor(Position source, Position target);

    State passTurn();

    List<Rank> ranks();

    String winner();

    State end();

    boolean isInit();

    boolean isRunning();

    boolean isFinished();

    boolean isNotEnd();
}
