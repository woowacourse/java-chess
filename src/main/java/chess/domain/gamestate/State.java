package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;

public interface State {

    State start();

    State move(Positions positions);

    State move(Position before, Position after);

    Camp switchCamp();

    State end();

    double statusOfBlack();

    double statusOfWhite();

    Board getBoard();

    GameResult getResult();

    boolean isRunning();
}
