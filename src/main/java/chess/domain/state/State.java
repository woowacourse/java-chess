package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public interface State {
    State start();

    State end();

    State move(Position source, Position target);

    Board board();

    boolean isFinished();

}
