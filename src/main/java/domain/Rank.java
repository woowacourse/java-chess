package domain;

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

    public List<Piece> getPieces() {
        return pieces;
    }
}
