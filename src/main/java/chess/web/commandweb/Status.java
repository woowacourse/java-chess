package chess.web.commandweb;

import chess.console.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class Status implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier<Map<String, Object>> returnModelToState) {
        chessGame.status();
        chessGame.run();
        return null;
    }
}
