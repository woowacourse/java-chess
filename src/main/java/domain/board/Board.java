package domain.board;

import domain.piece.Coordinate;
import domain.square.EmptySquare;
import domain.square.Square;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int RANK_SIZE = 8;

    private final List<Rank> ranks;

    public Board(final int fileSize) {
        this.ranks = initializeRanks(fileSize);
    }

    private List<Rank> initializeRanks(final int fileSize) {
        List<Rank> ranks = new ArrayList<>();
        for (int row = 0; row < RANK_SIZE; row++) {
            ranks.add(new Rank(row, fileSize));
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
        return ranks.get(start.getRow())
                .isMovableAt(start, end);
    }

    public boolean isSquareEmptyAt(final Coordinate target) {
        return !ranks.get(target.getRow())
                .isExistSquare(target.getCol());
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
