package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.Score;

public interface State {

    State start();

    State move(Position sourcePosition, Position targetPosition);

    State end();

    Score scoreOfWhite();

    Score scoreOfBlack();

    boolean isFinished();

    Board getBoard();

    Camp getWinner();
}
