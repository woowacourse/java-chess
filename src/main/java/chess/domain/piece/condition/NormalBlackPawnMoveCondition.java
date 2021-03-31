package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

public class NormalBlackPawnMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfiedBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isMovablePath(piece, target) &&
                isThereNoPieceOnPath(board, target) &&
                isNotTheChessPieceGoOffTheBoard(target);
    }

    private boolean isMovablePath(final ChessPiece piece, final Position target) {
        return target.equals(new Position(piece.getRow() + 1, piece.getColumn()));
    }

    private boolean isThereNoPieceOnPath(Board board, Position target) {
        return board.getAllPieces().stream()
                .noneMatch(piece -> piece.isSamePosition(target));
    }

}
