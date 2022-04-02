package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

import chess.domain.position.Column;
import chess.domain.position.Row;

public enum PieceGenerator {
    KING((file, rank) -> new PieceTypeChecker(file, rank).isKing(), rank -> new King(getColor(rank))),
    QUEEN((file, rank) -> new PieceTypeChecker(file, rank).isQueen(), rank -> new Queen(getColor(rank))),
    ROOK((file, rank) -> new PieceTypeChecker(file, rank).isRook(), rank -> new Rook(getColor(rank))),
    BISHOP((file, rank) -> new PieceTypeChecker(file, rank).isBishop(), rank -> new Bishop(getColor(rank))),
    KNIGHT((file, rank) -> new PieceTypeChecker(file, rank).isKnight(), rank -> new Knight(getColor(rank))),
    PAWN((file, rank) -> new PieceTypeChecker(file, rank).isPawn(), rank -> new Pawn(getColor(rank))),
    NONE((file, rank) -> new PieceTypeChecker(file, rank).isNone(), rank -> new None(getColor(rank)));

    private final BiPredicate<Column, Row> condition;
    private final Function<Row, Piece> of;

    PieceGenerator(BiPredicate<Column, Row> condition, Function<Row, Piece> of) {
        this.condition = condition;
        this.of = of;
    }

    static Piece generatePiece(Column column, Row row) {
        return Arrays.stream(PieceGenerator.values())
                .filter(piece -> piece.condition.test(column, row))
                .map(piece -> piece.of.apply(row))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static Color getColor(Row row) {
        if (row == Row.SEVEN || row == Row.EIGHT) {
            return Color.BLACK;
        }
        if (row == Row.ONE || row == Row.TWO) {
            return Color.WHITE;
        }
        return Color.NONE;
    }
}
