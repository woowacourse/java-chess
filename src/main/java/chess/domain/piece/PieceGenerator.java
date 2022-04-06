package chess.domain.piece;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.BiPredicate;
import java.util.function.Function;

import chess.domain.position.Column;
import chess.domain.position.Row;

public enum PieceGenerator {
    KING((column, row) -> new PieceTypeChecker(column, row).isKing(), row -> new King(getColor(row))),
    QUEEN((column, row) -> new PieceTypeChecker(column, row).isQueen(), row -> new Queen(getColor(row))),
    ROOK((column, row) -> new PieceTypeChecker(column, row).isRook(), row -> new Rook(getColor(row))),
    BISHOP((column, row) -> new PieceTypeChecker(column, row).isBishop(), row -> new Bishop(getColor(row))),
    KNIGHT((column, row) -> new PieceTypeChecker(column, row).isKnight(), row -> new Knight(getColor(row))),
    PAWN((column, row) -> new PieceTypeChecker(column, row).isPawn(), row -> new Pawn(getColor(row))),
    NONE((column, row) -> new PieceTypeChecker(column, row).isNone(), row -> new None(getColor(row)));

    private final BiPredicate<Column, Row> condition;
    private final Function<Row, Piece> of;

    PieceGenerator(BiPredicate<Column, Row> condition, Function<Row, Piece> of) {
        this.condition = condition;
        this.of = of;
    }

    public static Piece generatePiece(Column column, Row row) {
        return Arrays.stream(PieceGenerator.values())
                .filter(piece -> piece.condition.test(column, row))
                .map(piece -> piece.of.apply(row))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static PieceGenerator getType(Column column, Row row) {
        return Arrays.stream(PieceGenerator.values())
                .filter(piece -> piece.condition.test(column, row))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Color getColor(Row row) {
        if (row == Row.SEVEN || row == Row.EIGHT) {
            return Color.BLACK;
        }
        if (row == Row.ONE || row == Row.TWO) {
            return Color.WHITE;
        }
        return Color.NONE;
    }
}
