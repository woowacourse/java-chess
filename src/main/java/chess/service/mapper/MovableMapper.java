package chess.service.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Movable;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public final class MovableMapper {
    public static Movable from(final PieceType pieceType) {
        if (pieceType == PieceType.QUEEN) {
            return new Queen();
        }
        if (pieceType == PieceType.ROOK) {
            return new Rook();
        }
        if (pieceType == PieceType.KNIGHT) {
            return new Knight();
        }
        if (pieceType == PieceType.PAWN) {
            return new Pawn();
        }
        if (pieceType == PieceType.BISHOP) {
            return new Bishop();
        }
        return new King();
    }
}
