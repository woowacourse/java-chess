package domain.board;

import domain.piece.Coordinate;
import domain.square.EmptySquare;
import domain.square.Square;

import java.util.ArrayList;
import java.util.List;

public final class Board {

    public static final int RANK_SIZE = 8;
    public static final int FILE_SIZE = 8;

    private final List<Rank> ranks;

    public Board() {
        this.ranks = initializeRanks();
    }

    private List<Rank> initializeRanks() {
        List<Rank> ranks = new ArrayList<>();
        for (int row = 0; row < RANK_SIZE; row++) {
            ranks.add(new Rank(row, FILE_SIZE));
        }
        return ranks;
    }
    
    public Square findSquare(final Coordinate target) {
        Rank rankWithTargetSquare = ranks.get(target.getRow());
        return rankWithTargetSquare.findSquare(target.getCol());
    }

    public void replaceSquare(final Coordinate target, final Square square) {
        Rank rankWithTargetSquare = ranks.get(target.getRow());
        rankWithTargetSquare.replaceSquare(target.getCol(), square);
    }

    public void replaceWithEmptySquare(final Coordinate target) {
        Rank rankWithTargetSquare = ranks.get(target.getRow());
        rankWithTargetSquare.replaceSquare(target.getCol(), new EmptySquare());
    }

    public boolean isMovable(final Coordinate start, final Coordinate end) {
        validateOverBoardSize(start, end);
        return ranks.get(start.getRow())
                .isMovableAt(start, end);
    }

    private void validateOverBoardSize(final Coordinate start, final Coordinate end) {
        validateOverRankSize(start);
        validateOverFileSize(start);
        validateOverRankSize(end);
        validateOverFileSize(end);
    }

    private static void validateOverRankSize(final Coordinate target) {
        if (target.getRow() >= RANK_SIZE || target.getRow() < 0) {
            throw new IllegalArgumentException("[ERROR] 보드 Y축 좌표 범위를 벗어났습니다.");
        }
    }

    private static void validateOverFileSize(final Coordinate target) {
        if (target.getCol() >= FILE_SIZE || target.getCol() < 0) {
            throw new IllegalArgumentException("[ERROR] 보드 X축 좌표 범위를 벗어났습니다.");
        }
    }

    public boolean isSquareEmptyAt(final Coordinate target) {
        return !ranks.get(target.getRow())
                .isExistSquare(target.getCol());
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
