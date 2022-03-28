package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.Map;

public class ChessGame {

    private static final int FROM = 1;
    private static final int TO = 2;

    private State state = new Ready();

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.exit();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public void move(final String[] positions) {
        final Position from = Position.from(positions[FROM]);
        final Position to = Position.from(positions[TO]);

        state = state.move(from, to);
    }

    public Status status() {
        return state.status();
    }

    public boolean isExit() {
        return state.isExit();
    }
}
