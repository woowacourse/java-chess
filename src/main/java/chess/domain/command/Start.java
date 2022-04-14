package chess.domain.command;

import chess.domain.chessgame.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class Start implements CommandGenerator {

    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardInfoToState) {
        chessGame.run();

        printBoardInfoToState.run();
    }

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
