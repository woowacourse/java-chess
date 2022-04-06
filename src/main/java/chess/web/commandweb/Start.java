package chess.web.commandweb;

import chess.console.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class Start implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier returnModelToState) {
        System.err.println("start커맨드가 req된 것을 확인.");
        System.err.println("start커맨드가 req된 것을 확인.");

        chessGame.run();

//        printBoardInfoToState.run();
        final Object model = returnModelToState.get();
        System.err.println("start커맨드에서 return되는 모델은?" + model);
        return (Map<String, Object>) model;
    }
}
