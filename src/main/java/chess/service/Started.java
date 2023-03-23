package chess.service;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class Started implements State {

    private static final String STARTED_CANT_EXECUTE_START_MESSAGE = "시작된 상태에선 해당 명령을 실행할 수 없습니다.";

    private final Board board;

    Started(final Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(STARTED_CANT_EXECUTE_START_MESSAGE);
    }

    @Override
    public State move(final Position from, final Position to) {
        board.move(from, to);
        return this;
    }

    @Override
    public State end() {
        return End.getInstance();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
