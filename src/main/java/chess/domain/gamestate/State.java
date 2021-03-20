package chess.domain.gamestate;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.Side;
import chess.domain.position.Position;

public interface State {
    Board board();

    State start();

    State move(Position from, Position to);

    State status();

    boolean isGameSet();

    Score score();

    Side winner();

    Side side();
}
