package chess.status;

import chess.controller.Command;
import chess.controller.PrintAction;

public interface GameStatus {
    static GameStatus getInitialStatus() {
        return new Ready();
    }

    GameStatus playGame(Command command, PrintAction printAction);
}
