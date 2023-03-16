package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);
        validateCanMove(sourcePosition, targetPosition, sourcePiece, targetPiece);
        List<Position> path = sourcePiece.findPath(sourcePosition, targetPosition);
        validatePath(path);
        board.movePiece(sourcePosition, targetPosition);
    }

    private void validatePath(List<Position> path) {
        if (!board.isEmptyPosition(path)) {
            throw new IllegalArgumentException("경로가 없습니다.");
        }
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition, Piece sourcePiece,
                                 Piece targetPiece) {
        if (!sourcePiece.canMove(sourcePosition, targetPosition, targetPiece.getColor())) {
            throw new IllegalArgumentException("잘못된 위치를 지정하셨습니다.");
        }
    }
}
