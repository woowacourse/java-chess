package chess.web.commandweb;

import chess.console.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class End implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier returnModelToState) {

        if (!chessGame.isRunning()) {
            chessGame.gameSwitchOff();
            returnModelToState.get();
            return null;
        }

        chessGame.end();

        returnModelToState.get();

        chessGame.ready();
        return null;
    }
}
