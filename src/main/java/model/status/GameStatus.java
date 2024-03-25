package model.status;

import model.ChessGame;
import model.command.CommandLine;

public interface GameStatus {

    GameStatus play(CommandLine commandLine, ChessGame chessGame);

    boolean isRunning();
}
