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
        chessGame.move(movePositions);

        //returnModelToState.run();
        return null;
    }
}
