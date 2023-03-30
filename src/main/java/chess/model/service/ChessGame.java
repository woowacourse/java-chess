package chess.model.service;

import chess.model.domain.board.Score;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.domain.position.Position;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private State state;

    public ChessGame() {
        state = NotStarted.getInstance();
    }

    public void start() {
        state = state.start();
    }

    public void load(final Long id) {
        state = state.load(id);
    }

    public void move(final Position from, final Position to) {
        state = state.move(from, to);
    }

    public Map<Color, Score> status() {
        return state.status();
    }

    public void end() {
        state = state.end();
    }

    public boolean isEnd() {
        return state == End.getInstance();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public List<Long> findAllId() {
        return state.findAllId();
    }
}
