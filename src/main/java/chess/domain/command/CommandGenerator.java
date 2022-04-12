package chess.domain.command;

import chess.domain.chessgame.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public interface CommandGenerator {
    void execute(final String command,
                 final ChessGame chessGame,
                 final Runnable printBoardInfoToState);

    Map<String, Object> execute(final String command,
                                final ChessGame chessGame,
                                final Supplier<Map<String, Object>> returnModelToState);
}
