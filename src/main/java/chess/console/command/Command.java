package chess.console.command;

import chess.domain.GameManager;

public interface Command {

    void execute(GameManager gameManager);

    boolean isEnd();
}
