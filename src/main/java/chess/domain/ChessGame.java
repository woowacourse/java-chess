package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.move.MoveStrategy;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = board.getPiece(source);
        validateSourceBlank(sourcePiece);
        MoveStrategy moveStrategy = sourcePiece.getMoveStrategy();
        validateMove(source, target, moveStrategy);
        board.movePiece(source, target);
    }

    private void validateSourceBlank(final Piece sourcePiece) {
        if (sourcePiece.isBlank()) {
            throw new IllegalStateException("[ERROR] source에 Piece가 존재하지 않습니다.");
        }
    }

    private void validateMove(Position source, Position target, MoveStrategy moveStrategy) {
        if (!moveStrategy.isMovable(board, source, target)) {
            throw new IllegalStateException("[ERROR] 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
