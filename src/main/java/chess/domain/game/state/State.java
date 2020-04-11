package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Status;
import chess.domain.game.Turn;
import chess.domain.piece.Position;

public interface State {
    State start();

    State end();

    State move(Position source, Position target);

    Board board();

    Turn turn();

    Status status();

    boolean isFinished();
}
