package chess.domain.piece.generator;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

import chess.domain.piece.Color;
import chess.domain.piece.None;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.mulitiplemovepiece.Bishop;
import chess.domain.piece.mulitiplemovepiece.Queen;
import chess.domain.piece.mulitiplemovepiece.Rook;
import chess.domain.piece.unitmovepiece.King;
import chess.domain.piece.unitmovepiece.Knight;
import chess.domain.position.File;
import chess.domain.position.Rank;

public enum InitPieceGenerator {
    KING((file, rank) -> new InitPieceCondition(file, rank).isKing(), rank -> new King(getColor(rank))),
    QUEEN((file, rank) -> new InitPieceCondition(file, rank).isQueen(), rank -> new Queen(getColor(rank))),
    ROOK((file, rank) -> new InitPieceCondition(file, rank).isRook(), rank -> new Rook(getColor(rank))),
    BISHOP((file, rank) -> new InitPieceCondition(file, rank).isBishop(), rank -> new Bishop(getColor(rank))),
    KNIGHT((file, rank) -> new InitPieceCondition(file, rank).isKnight(), rank -> new Knight(getColor(rank))),
    PAWN((file, rank) -> new InitPieceCondition(file, rank).isPawn(), rank -> new Pawn(getColor(rank))),
    NONE((file, rank) -> new InitPieceCondition(file, rank).isNone(), rank -> new None(getColor(rank)));

    private final BiPredicate<File, Rank> condition;
    private final Function<Rank, Piece> of;

    InitPieceGenerator(BiPredicate<File, Rank> condition, Function<Rank, Piece> of) {
        this.condition = condition;
        this.of = of;
    }

    public static Piece generatePiece(File file, Rank rank) {
        return Arrays.stream(InitPieceGenerator.values())
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
