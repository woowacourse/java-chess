package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Position;

public interface State {

    State start();

    State move(Position beforePosition, Position afterPosition);

    State end();

    double statusOfBlack();

    double statusOfWhite();

    boolean isFinished();

    Board getBoard();

    int hasBlackWon();
}
