package chess.domain.board;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.Side;
import java.util.List;
import java.util.function.Function;

public enum InitialPosition {

    ROOK(List.of(File.A, File.H), nonPawnRank()),
    KNIGHT(List.of(File.B, File.G), nonPawnRank()),
    BISHOP(List.of(File.C, File.F), nonPawnRank()),
    QUEEN(List.of(File.D), nonPawnRank()),
    KING(List.of(File.E), nonPawnRank()),
    PAWN(List.of(File.values()), pawnRank()),
    ;

    private final List<File> files;
    private final Function<Side, Rank> rank;

    InitialPosition(List<File> files, Function<Side, Rank> rank) {
        this.files = files;
        this.rank = rank;
    }

    private static Function<Side, Rank> nonPawnRank() {
        return InitialPosition::nonPawnRankBySide;
    }

    private static Rank nonPawnRankBySide(Side side) {
        if (side.isBlack()) {
            return Rank.EIGHT;
        }
        if (side.isWhite()) {
            return Rank.ONE;
        }
        throw new IllegalArgumentException("진영은 검은색 또는 흰색이어야 합니다.");
    }

    private static Function<Side, Rank> pawnRank() {
        return InitialPosition::pawnRankBySide;
    }

    private static Rank pawnRankBySide(Side side) {
        if (side.isBlack()) {
            return Rank.SEVEN;
        }
        if (side.isWhite()) {
            return Rank.TWO;
        }
        throw new IllegalArgumentException("진영은 검은색 또는 흰색이어야 합니다.");
    }

    public List<Position> positions(Side side) {
        Rank rank = rank(side);

        return files().stream()
                .map(file -> new Position(file, rank))
                .toList();
    }

    private List<File> files() {
        return files;
    }

    private Rank rank(Side side) {
        return rank.apply(side);
    }
}
