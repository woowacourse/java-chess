package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.List;

public class FirstTurnBlackPawnMoveCondition extends MoveCondition {
    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                target.equals(new Position(piece.getRow() + 2, piece.getColumn())) &&
                isPieceExistOnPath(board, piece, target) &&
                validateChessPieceOutOfBoard(board, target);
    }

    private boolean isPieceExistOnPath(Board board, Piece piece, Position target) {
        List<Piece> pieces = board.getPieces();

        return pieces.stream()
                .filter(p -> !p.equals(piece))
                .noneMatch(p -> p.getColumn() == piece.getColumn() &&
                        piece.getRow() <= p.getRow() && p.getRow() <= target.getRow()
        );
    }
}
