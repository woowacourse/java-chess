package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.state.Status;

import java.util.Map;

public final class ChessGame {

    private static final int FROM = 0;
    private static final int TO = 1;

    private State state;

    public ChessGame(final State state) {
        this.state = state;
    }

    public ChessGame() {
        state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public void move(final String[] positions) {
        final Position from = Position.from(positions[FROM]);
        final Position to = Position.from(positions[TO]);

        state = state.move(from, to);
    }

    public void move(final Position from, final Position to) {
        state = state.move(from, to);
    }

    public Status status() {
        return new Status(
                state.getWinner(),
                Map.of(Color.WHITE, state.score(Color.WHITE)),
                Map.of(Color.BLACK, state.score(Color.BLACK))
        );
    }

    public boolean isEnd() {
        return state.isEnd();
    }
}
