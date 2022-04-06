package chess.web.commandweb;

import chess.console.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public interface WebCommandGenerator {
    Map<String, Object> execute(final String command,
                                final ChessGame chessGame,
                                final Supplier returnModelToState);
}
