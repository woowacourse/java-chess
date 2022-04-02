package chess.domain.state;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.postion.Position;

import java.util.List;


public interface State {

    State start();

    State end();

    boolean isGameOver();

    State changeTurn(List<Position> positions);

    Score status();

    Board board();
}
