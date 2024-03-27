package chess.domain.piece;

import chess.domain.color.Color;
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
    WHITE_KNIGHT(() -> new Knight(Color.WHITE)),
    WHITE_BISHOP(() -> new Bishop(Color.WHITE)),
    WHITE_ROOK(() -> new Rook(Color.WHITE)),
    WHITE_QUEEN(() -> new Queen(Color.WHITE)),
    WHITE_KING(() -> new King(Color.WHITE)),
    BLACK_FIRST_PAWN(BlackFirstPawn::new),
    BLACK_PAWN(BlackPawn::new),
    BLACK_KNIGHT(() -> new Knight(Color.BLACK)),
    BLACK_BISHOP(() -> new Bishop(Color.BLACK)),
    BLACK_ROOK(() -> new Rook(Color.BLACK)),
    BLACK_QUEEN(() -> new Queen(Color.BLACK)),
    BLACK_KING(() -> new King(Color.BLACK)),
    BLANK(Blank::new);

    private final Supplier<Piece> pieceMaker;

    PieceType(Supplier<Piece> pieceMaker) {
        this.pieceMaker = pieceMaker;
    }

    public Piece getPiece() {
        return pieceMaker.get();
    }
}
