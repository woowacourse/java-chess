package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.game.Score;
import chess.domain.game.Side;
import chess.domain.position.Position;

public interface State {

    State start();

    State move(Position from, Position to);

    State finished();

    Board board();

    Side currentTurn();

    Score score();

    Side winner();

    boolean isGameSet();

    boolean isFinished();
}
