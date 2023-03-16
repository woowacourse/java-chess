package domain.board;

import domain.square.EmptySquare;
import domain.square.Square;
import domain.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int RANK_COUNT = 8;
    public static final int FILE_COUNT = 8;

    // TODO: RANKS 일급컬렉션 지정
    private final List<Rank> ranks;

    public Board() {
        ranks = initialize();
    }

    public List<Rank> initialize() {
        List<Rank> ranks = new ArrayList<>();
        for (int row = 0; row < RANK_COUNT; row++) {
            ranks.add(new Rank(row, FILE_COUNT));
        }
        return ranks;
    }

    public void move(Coordinate startCoordinate, Coordinate endCoordinate) {
        if (isMovable(startCoordinate, endCoordinate)) {
            int startRow = startCoordinate.getRow();
            int startCol = startCoordinate.getCol();
            int endRow = endCoordinate.getRow();
            int endCol = endCoordinate.getCol();

            Square findSquare = ranks.get(startRow).findPiece(startCol);
            ranks.get(startRow).replacePiece(startCol, new EmptySquare());
            ranks.get(endRow).replacePiece(endCol, findSquare);
        }
    }

    private boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return isMovableByRule(startCoordinate, endCoordinate) &&
                !isPieceExistsAtEnd(endCoordinate) &&
                isNotBlocked(startCoordinate, endCoordinate);
    }

    private boolean isMovableByRule(Coordinate startCoordinate, Coordinate endCoordinate) {
        int row = startCoordinate.getRow();
        int col = startCoordinate.getCol();

        return ranks.get(row).isMovableAt(col, startCoordinate, endCoordinate);
    }

    private boolean isPieceExistsAtEnd(Coordinate endCoordinate) {
        return ranks.get(endCoordinate.getRow()).isExistPiece(endCoordinate.getCol());
    }

    private boolean isNotBlocked(Coordinate startCoordinate, Coordinate endCoordinate) {
        Square square = ranks.get(startCoordinate.getRow()).findPiece(startCoordinate.getCol());

        if (square.canReap()) {
            return true;
        }
        return isNotBlockedWhenNotReap(startCoordinate, endCoordinate);
    }

    private boolean isNotBlockedWhenNotReap(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);
        Coordinate indexCoordinate = startCoordinate.add(directionVector);

        while (!ranks.get(indexCoordinate.getRow()).isExistPiece(indexCoordinate.getCol()) &&
        !indexCoordinate.equals(endCoordinate)) {
            indexCoordinate = indexCoordinate.add(directionVector);
        }
        return indexCoordinate.equals(endCoordinate);
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
