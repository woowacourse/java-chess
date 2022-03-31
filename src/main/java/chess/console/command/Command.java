package chess.console.command;

import chess.GameManager;

public interface Command {

    void execute(GameManager gameManager);

    boolean isEnd();
}
