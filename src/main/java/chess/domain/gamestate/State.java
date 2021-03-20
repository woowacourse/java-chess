package chess.domain.gamestate;

import chess.domain.Score;
import chess.domain.Side;
import chess.domain.position.Position;

public interface State {

    State start();

    State gameSet();

    State move(Position from, Position to, Side side);

    State status();

    boolean isGameSet();

    Score score();
}
