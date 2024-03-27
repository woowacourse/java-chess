package factory;

import domain.*;
import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;

import domain.piece.kind.jumping.King;
import domain.piece.kind.jumping.Knight;
import domain.piece.kind.pawn.Pawn;
import domain.piece.kind.sliding.Bishop;
import domain.piece.kind.sliding.Queen;
import domain.piece.kind.sliding.Rook;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChessBoardGenerator {
    private ChessBoardGenerator() {
        throw new UnsupportedOperationException("생성할 수 없습니다");
    }

    public static ChessBoard createDefaultBoard() {
        return new ChessBoard(createDefaultPieces());
    }

    public static Pieces createDefaultPieces() {
        final var defaultPieces = new HashSet<Piece>();
        defaultPieces.addAll(selectPiece(Rank.EIGHT, Color.BLACK));
        defaultPieces.addAll(selectPawn(Rank.SEVEN, Color.BLACK));

        defaultPieces.addAll(selectPawn(Rank.TWO, Color.WHITE));
        defaultPieces.addAll(selectPiece(Rank.ONE, Color.WHITE));
        return new Pieces(defaultPieces);
    }

    private static Set<Piece> selectPawn(final Rank rank, final Color color) {
        return Arrays.stream(File.values())
                .map(file -> Pawn.from(new Point(file, rank), color))
                .map(Piece.class::cast)
                .collect(Collectors.toSet());
    }

    private static Set<Piece> selectPiece(final Rank rank, final Color color) {
        return Set.of(new Rook(new Point(File.A, rank), color), new Knight(new Point(File.B, rank), color),
                new Bishop(new Point(File.C, rank), color), new Queen(new Point(File.D, rank), color),
                new King(new Point(File.E, rank), color), new Bishop(new Point(File.F, rank), color),
                new Knight(new Point(File.G, rank), color), new Rook(new Point(File.H, rank), color));
    }
}
