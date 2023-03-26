package chess.controller.game;

import chess.service.GameService;
import java.util.List;

@FunctionalInterface
public interface GameAction {
    GameAction EMPTY = (gameService, commands) -> {
    };

    void execute(final GameService gameService, final List<String> commands);
}
