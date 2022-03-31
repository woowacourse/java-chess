package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.Map;
import java.util.function.ObjDoubleConsumer;

public class ChessGame {

    private static final int FROM = 1;
    private static final int TO = 2;

    private State state = new Ready();

    public void start() {
        state = state.start();
    }

    public void move(final String[] positions) {
        Position from = Position.from(positions[FROM]);
        Position to = Position.from(positions[TO]);

        state = state.move(from, to);
    }

    public void end() {
        state = state.end();
    }

    public void status(final ObjDoubleConsumer<String> printScore) {
        state.status(printScore);
    }

    public boolean isEnded() {
        return state.isEnded();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }
}
