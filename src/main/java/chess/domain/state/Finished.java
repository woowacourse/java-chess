package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class Finished implements ChessState {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public ChessState start() {
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
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
        return board.getPieces();
    }

    @Override
    public Status createStatus() {
        return new Status(board);
    }
}
