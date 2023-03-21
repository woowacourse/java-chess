package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Square {

    private static final Map<Integer, Square> cache = new HashMap<>();

    private final Rank rank;
    private final File file;

    private Square(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square of(final Rank rank, final File file) {
        return cache.computeIfAbsent(toKey(rank, file), key -> new Square(rank, file));
    }

    private static Integer toKey(final Rank rank, final File file) {
        return Objects.hash(rank, file);
    }

    public boolean hasSameRank(final Square to) {
        return rank == to.rank;
    }

    public boolean hasSameFile(final Square to) {
        return file == to.file;
    }

    public int rankDistanceTo(final Square to) {
        return rank.distanceTo(to.rank);
    }

    public int fileDistanceTo(final Square to) {
        return file.distanceTo(to.file);
    }

    public boolean hasBiggerRankThan(final Square to) {
        return rank.isBiggerThan(to.rank);
    }

    public boolean isStraight(final Square to) {
        return hasSameRank(to) || hasSameFile(to);
    }

    public boolean isDiagonal(final Square to) {
        return rankDistanceTo(to) == fileDistanceTo(to);
    }

    public List<Square> calculatePath(final Square to) {
        if (isStraight(to)) {
            return squaresOfStraight(to);
        }
        if (isDiagonal(to)) {
            return squaresOfDiagonal(to);
        }
        return Collections.emptyList();
    }

    private List<Square> squaresOfStraight(final Square to) {
        if (hasSameRank(to)) {
            return File.filesBetween(file, to.file)
                    .stream()
                    .map(foundFile -> Square.of(rank, foundFile))
                    .collect(Collectors.toUnmodifiableList());
        }
        return Rank.ranksBetween(rank, to.rank)
                .stream()
                .map(foundRank -> Square.of(foundRank, file))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Square> squaresOfDiagonal(final Square to) {
        List<Rank> ranks = Rank.ranksBetween(rank, to.rank);
        List<File> files = File.filesBetween(file, to.file);
        List<Square> squares = new ArrayList<>();
        for (int i = 0; i < ranks.size(); i++) {
            squares.add(Square.of(ranks.get(i), files.get(i)));
        }
        return squares;
    }

    public int getRank() {
        return rank.getPosition();
    }

    public File getFile() {
        return file;
    }
}
