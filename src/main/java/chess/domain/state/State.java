package chess.domain.state;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.postion.Position;


public interface State {

    State start();

    State end();

    State changeTurn(Position source, Position target);

    Score status();

    Board board();
}
