package chess.domain.gamestate;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.Side;
import chess.domain.position.Position;

public interface State {
    State start();

    State move(Position from, Position to);

    State status();

    State finished();

    Board board();

    Side side();

    Score score();

    Side winner();

    boolean isGameSet();

    boolean isFinished();
}
