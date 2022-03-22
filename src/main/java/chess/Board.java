package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Piece> pieces;

    public Board(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
        validateSize(pieces);
    }

    private void validateSize(final List<Piece> pieces) {
        if (pieces.size() != 32) {
            throw new IllegalArgumentException("시작 체스말은 32개여야 합니다.");
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "Board{" +
                "pieces=" + pieces +
                '}';
    }
}
