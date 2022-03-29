package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class Ready implements ChessState {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    private final Board board;

    public Ready(Board board) {
        this.board = board;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public ChessState start() {
        return new WhiteRunning(board);
    }

    @Override
    public ChessState move(Position start, Position target) {
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    @Override
    public ChessState end() {
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    @Override
    public Map<Position, Piece> getPieces() {
        throw new UnsupportedOperationException(CANNOT_IMPLEMENT_COMMAND);
    }

    @Override
    public Status createStatus() {
        throw new UnsupportedOperationException(CANNOT_IMPLEMENT_COMMAND);
    }
}
