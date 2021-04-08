package chess.domain.game.state;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Color;

import java.util.Map;
import java.util.Set;

public interface State {
    State start();

    void moveIfValidColor(Position source, Position target);

    State passTurn();

    State end();

    String finishReason();

    boolean isRunning();

    boolean isNotEnd();

    Map<Position, Piece> squares();

    Color winner();

    boolean isInit();

    Set<Position> movablePath(Position source);

    String toString();
}
