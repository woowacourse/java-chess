package chess.domain.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class DefaultMoveStrategy implements MoveStrategy {
    protected boolean isBlankPieceInsideBoard(Piece nextPiece) {
        return Position.isInBoardRange(nextPiece) && nextPiece.isBlank();
    }

    protected boolean isOpponentPieceInsideBoard(Piece piece, Piece nextPiece) {
        return Position.isInBoardRange(nextPiece) && piece.isOtherTeam(nextPiece);
    }
}
