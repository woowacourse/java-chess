package controller.menu;

import domain.ChessGame;

public interface Command {
    void execute(String command, ChessGame game);
}
