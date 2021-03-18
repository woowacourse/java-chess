package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.List;

public class FirstTurnWhitePawnMoveCondition extends MoveCondition {
    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                target.equals(new Position(piece.getRow() - 2, piece.getColumn())) &&
                isPieceExistOnPath(board, piece, target) &&
                validateChessPieceOutOfBoard(board, target);
    }

    private boolean isPieceExistOnPath(Board board, Piece piece, Position target) {
        List<Piece> pieces = board.getPieces();

        return pieces.stream()
                .filter(p -> !p.equals(piece))
                .noneMatch(
                        p -> p.getColumn() == piece.getColumn() && target.getRow() <= p.getRow() && p.getRow() <= piece.getRow()
                );
    }
}
