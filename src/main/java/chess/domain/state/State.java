package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Status;

public interface State {

    State start();

    State end();

    State move(Position from, Position to);

    ChessBoard getChessBoard();

    Status getStatus();

    boolean isFinished();
}
