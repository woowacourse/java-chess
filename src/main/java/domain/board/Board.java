package domain.board;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piecetype.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int RANK_COUNT = 8;
    public static final int FILE_COUNT = 8;

    private final List<Rank> ranks;

    public Board() {
        ranks = new ArrayList<>();
        for (int row = 0; row < RANK_COUNT; row++) {
            ranks.add(new Rank(row, FILE_COUNT));
        }
    }

    public void move(Coordinate startCoordinate, Coordinate endCoordinate) {
        if (isMovable(startCoordinate, endCoordinate)) {
            int startRow = startCoordinate.getRow();
            int startCol = startCoordinate.getCol();
            int endRow = endCoordinate.getRow();
            int endCol = endCoordinate.getCol();

            Piece findPiece = ranks.get(startRow).findPiece(startCol);
            ranks.get(startRow).replacePiece(startCol, new EmptyPiece());
            ranks.get(endRow).replacePiece(endCol, findPiece);
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
        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);
        Coordinate indexCoordinate = startCoordinate;

        while (!ranks.get(indexCoordinate.getRow()).isExistPiece(indexCoordinate.getCol())) {
            indexCoordinate = indexCoordinate.add(directionVector);
        }
        return indexCoordinate.equals(endCoordinate);
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
