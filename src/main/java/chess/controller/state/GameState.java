package chess.controller.state;

import chess.controller.GameCommand;
import chess.service.ChessService;

public interface GameState {
    GameState execute(GameCommand gameCommand);

    default boolean isEnd(ChessService service) {
        return this.getClass().equals(End.class) || !service.checkKingsAlive();
    }
}
