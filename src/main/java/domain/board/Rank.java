package domain.board;

import domain.piece.Piece;
import domain.piecetype.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Piece> pieces;

    public Rank(int row, int totalColCount) {
        this.pieces = new ArrayList<>();
        for (int col = 0; col < totalColCount; col++) {
            this.pieces.add(BoardInitialImage.getPieceByCoordinate(row, col));
        }
    }

    public Piece findPiece(int col) {
        return pieces.get(col);
    }

    public boolean isExistPiece(int col) {
        return pieces.get(col).isExist();
    }

    public void replacePiece(int col, Piece newPiece) {
        pieces.set(col, newPiece);
    }

    public boolean isMovableAt(int col, Coordinate startCoordinate, Coordinate endCoordinate) {
        Piece targetPiece = pieces.get(col);
        return targetPiece.isMovable(startCoordinate, endCoordinate);
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
