package chess.domain.command;

import chess.domain.chessgame.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class Status implements CommandGenerator {

    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardInfoToState) {
        chessGame.status();

        printBoardInfoToState.run();

        chessGame.run();
    }

    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier<Map<String, Object>> returnModelToState) {
        chessGame.status();
        chessGame.run();
        return null;
    }
}
