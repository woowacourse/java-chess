package chess.web.commandweb;

import chess.domain.chessgame.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class Start implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier<Map<String, Object>> returnModelToState) {
        if (chessGame.isEndInGameOff()) {
            chessGame.gameSwitchOn();
        }
        chessGame.runWithCurrentState();

        return returnModelToState.get();
    }
}
