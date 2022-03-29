package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Map;

public abstract class Running implements ChessState {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";


    protected final Board board;
    protected final Color color;

    public Running(Board board, Color color) {
        this.board = board;
        this.color = color;
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public ChessState start() {
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    @Override
    public ChessState move(Position start, Position target) {
        Piece caughtPiece = board.move(start, target, color);
        if (caughtPiece.isSamePiece(PieceType.KING)) {
            return new Finished(board);
        }
        return changeTurn();
    }

    abstract ChessState changeTurn();

    @Override
    public ChessState end() {
        return new Finished(board);
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
