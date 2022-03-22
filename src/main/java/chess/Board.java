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
        final List<Piece> pieces = new ArrayList<>();
        pieces.addAll(King.createKings());
        pieces.addAll(Queen.createQueens());
        pieces.addAll(Bishop.createBishops());
        pieces.addAll(Knight.createKnights());
        pieces.addAll(Rook.createRooks());
        pieces.addAll(Pawn.createPawns());
        System.out.println(pieces.size());
        return new Board(pieces);
    }

    private void validateSize(final List<Piece> pieces) {
        if (pieces.size() != 32) {
            throw new IllegalArgumentException("시작 체스말은 32개여야 합니다.");
        }
     }
}
