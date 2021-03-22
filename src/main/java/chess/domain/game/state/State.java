package chess.domain.game.state;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Color;

import java.util.List;
import java.util.Map;

public interface State {
    State start();

    void moveIfValidColor(Position source, Position target);

    boolean isSameColor(Color color);

    State passTurn();

    State end();

    boolean isNotFinished();

    List<Map<Position, Piece>> squares();
}
