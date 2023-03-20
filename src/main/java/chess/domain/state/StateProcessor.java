package chess.domain.state;

import chess.cache.PieceCache;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.CommandDto;

import java.util.Map;

public class StateProcessor {
    private State state;

    private StateProcessor(final State state) {
        this.state = state;
    }

    public static StateProcessor create() {
        final State state = new Ready(Board.from(PieceCache.create()));

        return new StateProcessor(state);
    }

    public final Map<Position, Piece> getBoard() {
        return state.board
                .getBoard();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean isNotEnd() {
        return !state.isEnd();
    }

    public State move(final CommandDto commandDto) {
        return state.move(commandDto.getSource(), commandDto.getTarget());
    }

    public State start(final CommandDto commandDto) {
        return state.start();
    }

    public State end(final CommandDto commandDto) {
        return state.end();
    }
}
