package view;

import domain.piece.Piece;
import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.BlackPawn;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.PieceRole;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.piece.piecerole.WhitePawn;
import java.util.Arrays;
import java.util.Locale;

public enum PieceMapper {
    BLACK_PAWN(BlackPawn.create(), "P"),
    WHITE_PAWN(WhitePawn.create(), "p"),
    ROOK(Rook.create(), "R"),
    KNIGHT(Knight.create(), "N"),
    BISHOP(Bishop.create(), "B"),
    QUEEN(Queen.create(), "Q"),
    KING(King.create(), "K"),
    ;

    private final PieceRole pieceRole;
    private final String symbol;

    PieceMapper(final PieceRole pieceRole, final String symbol) {
        this.pieceRole = pieceRole;
        this.symbol = symbol;
    }

    public static String symbol(final Piece piece) {
        String symbol = findByPiece(piece).symbol;
        if (piece.isWhite()) {
            return symbol.toLowerCase(Locale.ROOT);
        }
        return symbol;
    }

    private static PieceMapper findByPiece(final Piece piece) {
        return Arrays.stream(values())
                .filter(mapper -> piece.equalPieceRole(mapper.pieceRole))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
