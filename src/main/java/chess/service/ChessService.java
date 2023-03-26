package chess.service;

import chess.domain.board.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessService {

    private State state;

    public ChessService() {
        state = NotStarted.getInstance();
    }

    public void start() {
        state = state.start();
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
}
