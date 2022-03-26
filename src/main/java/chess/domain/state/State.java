package chess.domain.state;

import chess.domain.ChessBoard;

public interface State {

    State start();

    State end();

    State move(String source, String target);

    boolean isFinished();

    ChessBoard chessBoard();
}
