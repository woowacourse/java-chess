package chess.console.command;

import chess.console.GameManager;

public interface Command {

    void execute(GameManager gameManager);

    boolean isEnd();
}
