package controller.command;

import domain.game.ChessGame;

public interface CommandExecutor {
    void execute(ChessGame chessGame);
}
