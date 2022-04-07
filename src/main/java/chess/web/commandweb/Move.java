package chess.web.commandweb;

import chess.console.ChessGame;
import chess.domain.board.Positions;
import java.util.Map;
import java.util.function.Supplier;

public final class Move implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier returnModelToState) {
        final Positions movePositions = Positions.from(command);

        System.err.println("move 커맨드가 req된 것을 확인.");

        chessGame.move(movePositions);

        final Object model = returnModelToState.get();
        System.err.println("move 커맨드가 return되는 모델은?" + model);
        return (Map<String, Object>) model;
    }
}
