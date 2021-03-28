package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public interface State {

    State start();

    void moveIfValidColor(Position source, Position target);

    State passTurn();

    Board board();

    String winner();

    State end();

    boolean isInit();

    boolean isRunning();

    boolean isFinished();

    boolean isNotEnd();
}
