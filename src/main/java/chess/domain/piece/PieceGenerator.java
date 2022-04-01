package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

import chess.domain.position.File;
import chess.domain.position.Rank;

public enum PieceGenerator {
    KING((file, rank) -> new PieceTypeChecker(file, rank).isKing(), rank -> new King(getColor(rank))),
    QUEEN((file, rank) -> new PieceTypeChecker(file, rank).isQueen(), rank -> new Queen(getColor(rank))),
    ROOK((file, rank) -> new PieceTypeChecker(file, rank).isRook(), rank -> new Rook(getColor(rank))),
    BISHOP((file, rank) -> new PieceTypeChecker(file, rank).isBishop(), rank -> new Bishop(getColor(rank))),
    KNIGHT((file, rank) -> new PieceTypeChecker(file, rank).isKnight(), rank -> new Knight(getColor(rank))),
    PAWN((file, rank) -> new PieceTypeChecker(file, rank).isPawn(), rank -> new Pawn(getColor(rank))),
    NONE((file, rank) -> new PieceTypeChecker(file, rank).isNone(), rank -> new None(getColor(rank)));

    private final BiPredicate<File, Rank> condition;
    private final Function<Rank, Piece> of;

    PieceGenerator(BiPredicate<File, Rank> condition, Function<Rank, Piece> of) {
        this.condition = condition;
        this.of = of;
    }

    static Piece generatePiece(File file, Rank rank) {
        return Arrays.stream(PieceGenerator.values())
                .filter(piece -> piece.condition.test(file, rank))
                .map(piece -> piece.of.apply(rank))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static Color getColor(Rank rank) {
        if (rank == Rank.SEVEN || rank == Rank.EIGHT) {
            return Color.BLACK;
        }
        if (rank == Rank.ONE || rank == Rank.TWO) {
            return Color.WHITE;
        }
        return Color.NONE;
    }
}
