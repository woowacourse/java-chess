package refactorChess.domain.state;

import refactorChess.domain.board.ChessBoard;
import refactorChess.domain.board.Position;
import refactorChess.domain.game.Score;
import refactorChess.domain.game.Status;

public interface State {

    State start();

    State end();

    State move(Position from, Position to);

    ChessBoard getChessBoard();

    Score getScore();

    Status getStatus();

    boolean isFinished();
}
