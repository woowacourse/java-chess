package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.function.Predicate;

public class FirstTurnWhitePawnMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovePath(piece, target) &&
                isNotPieceExistOnPath(board, piece, target) &&
                isNotChessPieceOutOfBoard(board, target);
    }

    private boolean isRightMovePath(final Piece piece, final Position target) {
        return target.equals(new Position(piece.getRow() - 2, piece.getColumn()));
    }

    private boolean isNotPieceExistOnPath(Board board, Piece piece, Position target) {
        return board.isNoneMatchByFilteredPieces(pieceOnBoard -> !pieceOnBoard.equals(piece),
                isExistInMoveArea(piece, target));
    }

    private Predicate<Piece> isExistInMoveArea(final Piece piece, final Position target) {
        return pieceOnBoard -> pieceOnBoard.getColumn() == piece.getColumn() &&
                target.getRow() <= pieceOnBoard.getRow() && pieceOnBoard.getRow() <= piece.getRow();
    }

}
