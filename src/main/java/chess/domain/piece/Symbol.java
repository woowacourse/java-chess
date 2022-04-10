package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public enum Symbol {
    ROOK_BLACK("rb", 5.0, () -> PieceFactory.getInstance("rb")),
    KNIGHT_BLACK("nb", 2.5, () -> PieceFactory.getInstance("rb")),
    BISHOP_BLACK("bb", 3.0, () -> PieceFactory.getInstance("rb")),
    QUEEN_BLACK("qb", 9.0, () -> PieceFactory.getInstance("qb")),
    KING_BLACK("kb", 0.0, () -> PieceFactory.getInstance("kb")),
    PAWN_BLACK("pb", 1.0, () -> PieceFactory.getInstance("pb")),
    ROOK_WHITE("rw", 5.0, () -> PieceFactory.getInstance("rw")),
    KNIGHT_WHITE("nw", 2.5, () -> PieceFactory.getInstance("nw")),
    BISHOP_WHITE("bw", 3.0, () -> PieceFactory.getInstance("bw")),
    QUEEN_WHITE("qw", 9.0, () -> PieceFactory.getInstance("qw")),
    KING_WHITE("kw", 0.0, () -> PieceFactory.getInstance("kw")),
    PAWN_WHITE("pw", 1.0, () -> PieceFactory.getInstance("pw"));

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
