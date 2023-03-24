package chess.dto;

import chess.domain.Position;
import chess.view.GameState;

public class ChessInputDto {
    private final GameState gameState;
    private final Position source;
    private final Position target;

    private ChessInputDto(final GameState gameState, final Position source, final Position target) {
        this.gameState = gameState;
        this.source = source;
        this.target = target;
    }

    public static ChessInputDto from(final GameState gameState, final String source, final String target) {
        return new ChessInputDto(gameState, Position.from(source), Position.from(target));
    }

    public static ChessInputDto from(final GameState gameState) {
         return new ChessInputDto(gameState, Position.NOT_EXISTS, Position.NOT_EXISTS);
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
