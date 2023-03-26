package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Square {
    private static final int MAX_RANK_VALUE = 8;
    private static final List<Square> CASHED_SQUARES;

    static {
        CASHED_SQUARES = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .collect(Collectors.toList());
    }

    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square getInstanceOf(File file, Rank rank) {
        int fileValue = file.getValue();
        int rankValue = rank.getValue();
        int index = (MAX_RANK_VALUE - rankValue) * MAX_RANK_VALUE + fileValue;

        return CASHED_SQUARES.get(index - 1);
    }

    public boolean isSameRank(Square targetSquare) {
        return rank == targetSquare.rank;
    }

    public boolean isSameFile(Square targetSquare) {
        return file == targetSquare.file;
    }

    public int calculateRankDistance(Square targetSquare) {
        return rank.calculateDistance(targetSquare.rank);
    }

    public int calculateFileDistance(Square targetSquare) {
        return file.calculateDistance(targetSquare.file);
    }

    public int calculateRankDifference(Square targetSquare) {
        return rank.calculateDifference(targetSquare.rank);
    }

    public List<Square> getSquaresInSameRank(Square square) {
        return file.getFilesInRange(square.file)
                .stream()
                .map(file -> Square.getInstanceOf(file, rank))
                .collect(Collectors.toList());
    }

    public List<Square> getSquaresInSameFile(Square square) {
        return rank.getRanksInRange(square.rank)
                .stream()
                .map(rank -> Square.getInstanceOf(file, rank))
                .collect(Collectors.toList());
    }

    public List<Square> getDiagonalSquares(Square square) {
        List<Square> squares = new ArrayList<>();
        List<File> files = file.getFilesInRange(square.file);
        List<Rank> ranks = rank.getRanksInRange(square.rank);

        for (int i = 0, end = files.size(); i < end; i++) {
            squares.add(Square.getInstanceOf(files.get(i), ranks.get(i)));
        }

        return squares;
    }

    public boolean isBlackPawnInitialRank() {
        return rank == Rank.SEVEN;
    }

    public boolean isWhitePawnInitialRank() {
        return rank == Rank.TWO;
    }

    public boolean reachedEndRank() {
        return rank == Rank.ONE || rank == Rank.EIGHT;
    }
}
