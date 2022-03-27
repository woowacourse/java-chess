package console.command;

import chess.ChessBoard;

public interface Command {

    boolean isRunning();

    ChessBoard execute(ChessBoard chessBoard);
}
