package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public enum Symbol {
    ROOK_BLACK("rb", 5.0, () -> new Rook(Color.BLACK)),
    KNIGHT_BLACK("nb", 2.5, () -> new Knight(Color.BLACK)),
    BISHOP_BLACK("bb", 3.0, () -> new Bishop(Color.BLACK)),
    QUEEN_BLACK("qb", 9.0, () -> new Queen(Color.BLACK)),
    KING_BLACK("kb", 0.0, () -> new King(Color.BLACK)),
    PAWN_BLACK("pb", 1.0, () -> new Pawn(Color.BLACK)),
    ROOK_WHITE("rw", 5.0, () -> new Rook(Color.WHITE)),
    KNIGHT_WHITE("nw", 2.5, () -> new Knight(Color.WHITE)),
    BISHOP_WHITE("bw", 3.0, () -> new Bishop(Color.WHITE)),
    QUEEN_WHITE("qw", 9.0, () -> new Queen(Color.WHITE)),
    KING_WHITE("kw", 0.0, () -> new King(Color.WHITE)),
    PAWN_WHITE("pw", 1.0, () -> new Pawn(Color.WHITE));

    private final String viewValue;
    private final double score;
    private final Supplier<Piece> pieceSupplier;

    Symbol(String viewValue, double score, Supplier<Piece> pieceSupplier) {
        this.viewValue = viewValue;
        this.score = score;
        this.pieceSupplier = pieceSupplier;
    }

    public static double getScore(Symbol symbol) {
        final Symbol found = Arrays.stream(values())
                .filter(value -> value == symbol)
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return found.score;
    }

    public static String getName(Symbol symbol) {
        final Symbol found = Arrays.stream(values())
                .filter(value -> value == symbol)
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return found.viewValue;
    }

    static Piece createPieceByName(String pieceName) {
        final Symbol found = Arrays.stream(values())
                .filter(symbol -> symbol.viewValue.equalsIgnoreCase(pieceName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return found.pieceSupplier.get();
    }
}
