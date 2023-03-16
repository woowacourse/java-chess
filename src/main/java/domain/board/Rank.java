package domain.board;

import domain.square.Square;
import domain.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Square> squares;

    public Rank(int row, int totalColCount) {
        this.squares = new ArrayList<>();
        for (int col = 0; col < totalColCount; col++) {
            this.squares.add(BoardInitialImage.getPieceByCoordinate(row, col));
        }
    }

    public Square findPiece(int col) {
        return squares.get(col);
    }

    public boolean isExistPiece(int col) {
        return squares.get(col).isExist();
    }

    public void replacePiece(int col, Square newSquare) {
        squares.set(col, newSquare);
    }

    public boolean isMovableAt(int col, Coordinate startCoordinate, Coordinate endCoordinate) {
        Square targetSquare = squares.get(col);
        return targetSquare.isMovable(startCoordinate, endCoordinate);
    }

    public List<Square> getPieces() {
        return squares;
    }
}
