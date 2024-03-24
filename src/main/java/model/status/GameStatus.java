package model.status;

import model.ChessBoard;
import model.command.CommandLine;

public interface GameStatus {

    GameStatus play(CommandLine commandLine, ChessBoard chessBoard);

    boolean isRunning();
}
