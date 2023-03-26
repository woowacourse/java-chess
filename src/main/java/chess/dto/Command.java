package chess.dto;

import chess.domain.Position;
import chess.view.GameState;

public class Command {
    private final GameState gameState;
    private final Position source;
    private final Position target;

    private Command(final GameState gameState, final Position source, final Position target) {
        this.gameState = gameState;
        this.source = source;
        this.target = target;
    }

    public static Command from(final GameState gameState, final String source, final String target) {
        return new Command(gameState, Position.from(source), Position.from(target));
    }

    public static Command from(final GameState gameState) {
         return new Command(gameState, Position.NOT_EXISTS, Position.NOT_EXISTS);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
