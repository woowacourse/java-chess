package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class Rank {

    private static final int SIZE = 8;
    private static final String SIZE_EXCEPTION_MESSAGE = "한 랭크는 8칸 이어야 합니다.";

    private final List<Piece> pieces;

    public Rank(List<Piece> pieces) {
        validate(pieces);
        this.pieces = new ArrayList<>(pieces);
    }

    private void validate(List<Piece> pieces) {
        if (pieces.size() != SIZE) {
            throw new IllegalArgumentException(SIZE_EXCEPTION_MESSAGE);
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
