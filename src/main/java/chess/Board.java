package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Piece> pieces;

    private Board(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
        validateSize(pieces);
    }

    public static Board init() {
        return null;
    }

    private void validateSize(final List<Piece> pieces) {
        if (pieces.size() != 32) {
            throw new IllegalArgumentException("시작 체스말은 32개여야 합니다.");
        }
     }
}
