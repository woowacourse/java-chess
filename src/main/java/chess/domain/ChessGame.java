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

    private State state = new Ready();

    public void start() {
        state = state.start();
        // initBoard db에 저장하기
    }

    public void end() {
        state = state.end();
        // db 보드 내역 삭제
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public void move(final String[] positions) {
        final Position from = Position.from(positions[FROM]);
        final Position to = Position.from(positions[TO]);

        state = state.move(from, to);
        // db에 이동내역 저장하기
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

    public boolean removedKing() {
        return state.removedKing();
    }
}
