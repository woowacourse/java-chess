package dao;

import domain.piece.Color;
import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.Pawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceConverter {

    PAWN(Pawn.class, "pawn", (color) -> {
        if (color.equals(Color.BLACK)) {
            return new BlackPawn(color);
        }
        return new WhitePawn(color);
    }),
    ROOK(Rook.class, "rook", Rook::new),
    KNIGHT(Knight.class, "knight", Knight::new),
    BISHOP(Bishop.class, "bishop", Bishop::new),
    QUEEN(Queen.class, "queen", Queen::new),
    KING(King.class, "king", King::new),
    EMPTY(EmptyPiece.class, "empty", EmptyPiece::new),
    ;

    private final Class<? extends Piece> pieceType;
    private final String parsed;
    private final Function<Color, Piece> generator;

    PieceConverter(
            final Class<? extends Piece> pieceType,
            final String parsed,
            final Function<Color, Piece> generator
    ) {
        this.pieceType = pieceType;
        this.parsed = parsed;
        this.generator = generator;
    }

    public static String parse(final Piece target) {
        return Arrays.stream(values())
                .filter(pieceStringParser -> pieceStringParser.pieceType.isInstance(target))
                .findFirst()
                .orElseThrow()
                .parsed;
    }

    public static Piece combine(final String target, final Color color) {
        return Arrays.stream(values())
                .filter(pieceStringParser -> pieceStringParser.parsed.equals(target))
                .findFirst()
                .orElseThrow()
                .generator
                .apply(color);
    }
}
