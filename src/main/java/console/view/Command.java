package console.view;

import chess.ChessBoard;

public interface Command {

    boolean isFinished();

    ChessBoard execute(ChessBoard chessBoard);
}
