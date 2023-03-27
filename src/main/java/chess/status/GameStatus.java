package chess.status;

import chess.controller.Command;
import chess.controller.PrintAction;
import chess.dao.ChessGameDaoImpl;

public interface GameStatus {
    static GameStatus getInitialStatus() {
        return new Ready(new ChessGameDaoImpl());
    }

    GameStatus playGame(Command command, PrintAction printAction);
}
