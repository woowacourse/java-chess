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
            //db 저장하면 될 듯.
            return null;
        }

        chessGame.end();

        returnModelToState.get();

//        chessGame.ready();
        // 게임은 ready로 돌리고 render는 다시 index.html로 돌아가도록 해보자.
        return null;
    }
}
