package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.List;
import java.util.function.Predicate;

public class FirstTurnWhitePawnMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfyBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightMovePath(piece, target) &&
                isNotExistPieceOnPath(board, piece, target) &&
                isNotChessPieceOutOfBoard(target);
    }

    private boolean isRightMovePath(final ChessPiece piece, final Position target) {
        return target.equals(new Position(piece.getRow() - 2, piece.getColumn()));
    }

    private boolean isNotExistPieceOnPath(Board board, ChessPiece piece, Position target) {
        List<ChessPiece> pieces = board.getAllPieces();

        return pieces.stream()
                .filter(pieceOnBoard -> !pieceOnBoard.equals(piece))
                .noneMatch(isExistInMoveArea(piece, target));
    }

    private Predicate<ChessPiece> isExistInMoveArea(final ChessPiece piece, final Position target) {
        return pieceOnBoard -> pieceOnBoard.getColumn() == piece.getColumn() &&
                target.getRow() <= pieceOnBoard.getRow() && pieceOnBoard.getRow() <= piece.getRow();
    }

}
