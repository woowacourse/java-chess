package domain.board;

import domain.square.Square;
import domain.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;

public final class Rank {

    private final List<Square> squares;

    public Rank(final int row, final int totalColCount) {
        squares = initialize(row, totalColCount);
    }

    private List<Square> initialize(final int row, final int totalColCount) {
        final List<Square> squares = new ArrayList<>();
        for (int col = 0; col < totalColCount; col++) {
            squares.add(BoardInitialImage.getPieceByCoordinate(row, col));
        }
        return squares;
    }

    public Square findSquare(final int col) {
        return squares.get(col);
    }

    public boolean isExistSquare(final int col) {
        return squares.get(col).isExist();
    }

    public void replaceSquare(final int col, final Square newSquare) {
        squares.set(col, newSquare);
        squares.get(col).checkMoved();
    }

    public boolean isMovableAt(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Square targetSquare = squares.get(startCoordinate.getCol());
        return targetSquare.isMovable(startCoordinate, endCoordinate);
    }

    public List<Square> getSquares() {
        return squares;
    }
}
