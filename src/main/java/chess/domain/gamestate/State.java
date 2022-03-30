package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.Position;

public interface State {

    State start();

    State move(Position beforePosition, Position afterPosition);

    Camp switchCamp();

    State end();

    double statusOfBlack();

    double statusOfWhite();

    Board getBoard();

    Result getResult();

    boolean isRunning();
}
