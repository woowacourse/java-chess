package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.position.File;
import domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingRule {

    public Piece findPieceByPosition(Rank rank, File file) {
        Color color = findColor(rank);
        PieceType pieceType = TypeFinder.find(rank, file);
        return new Piece(pieceType, color);
    }

    private Color findColor(Rank rank) {
        if (rank == Rank.ONE || rank == Rank.TWO) {
            return Color.WHITE;
        }
        if (rank == Rank.SEVEN || rank == Rank.EIGHT) {
            return Color.BLACK;
        }
        return Color.NONE;
    }

    private enum TypeFinder {
        PAWN(PieceType.PAWN, List.of(Rank.TWO, Rank.SEVEN), List.of(File.values())),
        ROOK(PieceType.ROOK, List.of(Rank.ONE, Rank.EIGHT), List.of(File.A, File.H)),
        KNIGHT(PieceType.KNIGHT, List.of(Rank.ONE, Rank.EIGHT), List.of(File.B, File.G)),
        BISHOP(PieceType.BISHOP, List.of(Rank.ONE, Rank.EIGHT), List.of(File.C, File.F)),
        QUEEN(PieceType.QUEEN, List.of(Rank.ONE, Rank.EIGHT), List.of(File.D)),
        KING(PieceType.KING, List.of(Rank.ONE, Rank.EIGHT), List.of(File.E)),
        NONE(PieceType.NONE, new ArrayList<>(), new ArrayList<>()),
        ;

        private final PieceType pieceType;
        private final List<Rank> ranks;
        private final List<File> files;

        TypeFinder(PieceType pieceType, List<Rank> ranks, List<File> files) {
            this.pieceType = pieceType;
            this.ranks = ranks;
            this.files = files;
        }

        private static PieceType find(Rank rank, File file) {
            return Arrays.stream(values())
                    .filter((typeFinder -> typeFinder.ranks.contains(rank) && typeFinder.files.contains(file)))
                    .findFirst()
                    .orElse(TypeFinder.NONE)
                    .pieceType;
        }
    }
}
