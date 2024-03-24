package chess.domain.position;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Position {
    A1(ChessFile.A, ChessRank.ONE), B1(ChessFile.B, ChessRank.ONE), C1(ChessFile.C, ChessRank.ONE), D1(ChessFile.D, ChessRank.ONE), E1(ChessFile.E, ChessRank.ONE), F1(ChessFile.F, ChessRank.ONE), G1(ChessFile.G, ChessRank.ONE), H1(ChessFile.H, ChessRank.ONE),
    A2(ChessFile.A, ChessRank.TWO), B2(ChessFile.B, ChessRank.TWO), C2(ChessFile.C, ChessRank.TWO), D2(ChessFile.D, ChessRank.TWO), E2(ChessFile.E, ChessRank.TWO), F2(ChessFile.F, ChessRank.TWO), G2(ChessFile.G, ChessRank.TWO), H2(ChessFile.H, ChessRank.TWO),
    A3(ChessFile.A, ChessRank.THREE), B3(ChessFile.B, ChessRank.THREE), C3(ChessFile.C, ChessRank.THREE), D3(ChessFile.D, ChessRank.THREE), E3(ChessFile.E, ChessRank.THREE), F3(ChessFile.F, ChessRank.THREE), G3(ChessFile.G, ChessRank.THREE), H3(ChessFile.H, ChessRank.THREE),
    A4(ChessFile.A, ChessRank.FOUR), B4(ChessFile.B, ChessRank.FOUR), C4(ChessFile.C, ChessRank.FOUR), D4(ChessFile.D, ChessRank.FOUR), E4(ChessFile.E, ChessRank.FOUR), F4(ChessFile.F, ChessRank.FOUR), G4(ChessFile.G, ChessRank.FOUR), H4(ChessFile.H, ChessRank.FOUR),
    A5(ChessFile.A, ChessRank.FIVE), B5(ChessFile.B, ChessRank.FIVE), C5(ChessFile.C, ChessRank.FIVE), D5(ChessFile.D, ChessRank.FIVE), E5(ChessFile.E, ChessRank.FIVE), F5(ChessFile.F, ChessRank.FIVE), G5(ChessFile.G, ChessRank.FIVE), H5(ChessFile.H, ChessRank.FIVE),
    A6(ChessFile.A, ChessRank.SIX), B6(ChessFile.B, ChessRank.SIX), C6(ChessFile.C, ChessRank.SIX), D6(ChessFile.D, ChessRank.SIX), E6(ChessFile.E, ChessRank.SIX), F6(ChessFile.F, ChessRank.SIX), G6(ChessFile.G, ChessRank.SIX), H6(ChessFile.H, ChessRank.SIX),
    A7(ChessFile.A, ChessRank.SEVEN), B7(ChessFile.B, ChessRank.SEVEN), C7(ChessFile.C, ChessRank.SEVEN), D7(ChessFile.D, ChessRank.SEVEN), E7(ChessFile.E, ChessRank.SEVEN), F7(ChessFile.F, ChessRank.SEVEN), G7(ChessFile.G, ChessRank.SEVEN), H7(ChessFile.H, ChessRank.SEVEN),
    A8(ChessFile.A, ChessRank.EIGHT), B8(ChessFile.B, ChessRank.EIGHT), C8(ChessFile.C, ChessRank.EIGHT), D8(ChessFile.D, ChessRank.EIGHT), E8(ChessFile.E, ChessRank.EIGHT), F8(ChessFile.F, ChessRank.EIGHT), G8(ChessFile.G, ChessRank.EIGHT), H8(ChessFile.H, ChessRank.EIGHT);

    private final ChessFile file;
    private final ChessRank rank;

    Position(final ChessFile file, final ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final ChessFile file, final ChessRank rank) {
        return Arrays.stream(values())
                .filter(position -> position.file == file && position.rank == rank)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("%s%s에 해당하는 위치정보를 찾을 수 없습니다.", file, rank)));
    }

    public Set<Position> findBetween(final Position target) {
        List<ChessRank> betweenRanks = ChessRank.findBetween(this.rank, target.rank);
        List<ChessFile> betweenFiles = ChessFile.findBetween(this.file, target.file);

        if (betweenRanks.isEmpty()) {
            return findHorizontalPositions(betweenFiles);
        }
        if (betweenFiles.isEmpty()) {
            return findVerticalPositions(betweenRanks);
        }
        return findDiagonalPositions(betweenFiles, betweenRanks);
    }

    private Set<Position> findHorizontalPositions(final List<ChessFile> betweenFiles) {
        Set<Position> positions = new HashSet<>();
        for (ChessFile file : betweenFiles) {
            positions.add(Position.of(file, this.rank));
        }
        return positions;
    }

    private Set<Position> findVerticalPositions(final List<ChessRank> betweenRanks) {
        Set<Position> positions = new HashSet<>();
        for (ChessRank rank : betweenRanks) {
            positions.add(Position.of(this.file, rank));
        }
        return positions;
    }

    private Set<Position> findDiagonalPositions(final List<ChessFile> betweenFiles, final List<ChessRank> betweenRanks) {
        Set<Position> positions = new HashSet<>();
        for (int i = 0; i < betweenFiles.size(); i++) {
            positions.add(Position.of(betweenFiles.get(i), betweenRanks.get(i)));
        }
        return positions;
    }

    public int calculateDistanceTo(final Position target) {
        int fileDistance = calculateFileDistanceTo(target);
        int rankDistance = calculateRankDistanceTo(target);

        if (fileDistance > 0) {
            return fileDistance;
        }
        return rankDistance;
    }

    public int calculateFileDistanceTo(final Position target) {
        return Math.abs(target.file.index() - file.index());
    }

    public int calculateRankDistanceTo(final Position target) {
        return Math.abs(target.rank.index() - rank.index());
    }

    public boolean isRank(final ChessRank rank) {
        return this.rank == rank;
    }

    public int indexOfFile() {
        return file.index();
    }

    public int indexOfRank() {
        return rank.index();
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
