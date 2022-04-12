package chess.web.commandweb;

import chess.domain.chessgame.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class End implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier<Map<String, Object>> returnModelToState) {
        if (!chessGame.isRunning()) {
            chessGame.gameSwitchOff();
        }

        chessGame.end();
        final Map<String, Object> model = returnModelToState.get();

        chessGame.ready();
        return model;
    }
}
