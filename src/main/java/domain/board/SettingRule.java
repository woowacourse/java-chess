package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingRule {

    public Piece findPieceByPosition(Rank rank, File file) {
        Color color = findColor(rank);
        Type type = TypeFinder.find(rank, file);
        return new Piece(type, color);
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
        PAWN(Type.PAWN, List.of(Rank.TWO, Rank.SEVEN), List.of(File.values())),
        ROOK(Type.ROOK, List.of(Rank.ONE, Rank.EIGHT), List.of(File.A, File.H)),
        KNIGHT(Type.KNIGHT, List.of(Rank.ONE, Rank.EIGHT), List.of(File.B, File.G)),
        BISHOP(Type.BISHOP, List.of(Rank.ONE, Rank.EIGHT), List.of(File.C, File.F)),
        QUEEN(Type.QUEEN, List.of(Rank.ONE, Rank.EIGHT), List.of(File.D)),
        KING(Type.KING, List.of(Rank.ONE, Rank.EIGHT), List.of(File.E)),
        NONE(Type.NONE, new ArrayList<>(), new ArrayList<>()),
        ;

        private final Type type;
        private final List<Rank> ranks;
        private final List<File> files;

        TypeFinder(Type type, List<Rank> ranks, List<File> files) {
            this.type = type;
            this.ranks = ranks;
            this.files = files;
        }

        private static Type find(Rank rank, File file) {
            return Arrays.stream(values())
                    .filter((typeFinder -> typeFinder.ranks.contains(rank) && typeFinder.files.contains(file)))
                    .findFirst()
                    .orElse(TypeFinder.NONE)
                    .type;
        }
    }
}
