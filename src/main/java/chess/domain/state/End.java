package chess.domain.state;

import chess.Command;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class End implements ChessState {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    private final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public ChessState execute(Command command, String... commandArgs) {
        throw new UnsupportedOperationException(CANNOT_IMPLEMENT_COMMAND);
    }

    @Override
    public boolean isEnd() {
        return true;
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
