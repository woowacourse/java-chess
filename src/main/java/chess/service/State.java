package chess.service;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public interface State {

    State start();

    State move(Board board, List<Position> positions);

    State end();
}
