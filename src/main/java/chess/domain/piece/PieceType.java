package chess.domain.piece;

import chess.domain.piece.blank.Blank;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.BlackFirstPawn;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;
import java.util.function.Supplier;

public enum PieceType {
    WHITE_FIRST_PAWN(WhiteFirstPawn::new),
    WHITE_PAWN(WhitePawn::new),
    WHITE_KNIGHT(Knight::createWhiteKnight),
    WHITE_BISHOP(Bishop::createWhiteBishop),
    WHITE_ROOK(Rook::createWhiteRook),
    WHITE_QUEEN(Queen::createWhiteQueen),
    WHITE_KING(King::createWhiteKing),
    BLACK_FIRST_PAWN(BlackFirstPawn::new),
    BLACK_PAWN(BlackPawn::new),
    BLACK_KNIGHT(Knight::createBlackKnight),
    BLACK_BISHOP(Bishop::createBlackBishop),
    BLACK_ROOK(Rook::createBlackRook),
    BLACK_QUEEN(Queen::createBlackQueen),
    BLACK_KING(King::createBlackKing),
    BLANK(Blank::new);

    private final Supplier<Piece> pieceMaker;

    PieceType(Supplier<Piece> pieceMaker) {
        this.pieceMaker = pieceMaker;
    }

    public Piece getPiece() {
        return pieceMaker.get();
    }
}
