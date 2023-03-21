package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class ChessService {

    private Board board;
    private State state;

    public ChessService() {
        state = NotStarted.getInstance();
    }

    public void start() {
        state = state.start();
        board = new BoardFactory().createInitialBoard();
    }

    public void move(final List<Position> positions) {
        state = state.move(board, positions);
    }

    public void end() {
        state = state.end();
    }

    public boolean isEnd() {
        return state == End.getInstance();
    }

    public Map<Position, Piece> getBoard() {
        assert (state != NotStarted.getInstance());
        return board.getBoard();
    }
}
