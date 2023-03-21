package chess.domain.board;

import chess.domain.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public final class Rank {

    private static final int RANK_SIZE = 8;
    static final String INVALID_RANK_SIZE = "[ERROR] Square의 개수가 " + RANK_SIZE + "개가 아닌 List가 들어왔습니다. 입력된 사이즈: ";

    private final List<Square> rank;

    public Rank(final List<Square> rank) {
        validateSize(rank);
        this.rank = new ArrayList<>(rank);
    }

    private void validateSize(final List<Square> rank) {
        if (rank.size() != RANK_SIZE) {
            throw new IllegalArgumentException(INVALID_RANK_SIZE + rank.size());
        }
    }

    public boolean isEmptyPiece(final int index) {
        return findSquare(index).isEmptyPiece();
    }

    public Square findSquare(final int index) {
        return rank.get(index);
    }

    public void replaceSquare(final int index, final Square square) {
        rank.set(index, square);
    }

    public Square replacePiece(final int index, final Piece newPiece) {
        Square square = findSquare(index);
        return square.replacePiece(newPiece);
    }

    public Piece findPiece(final int index) {
        Square square = findSquare(index);
        return square.getPiece();
    }

    public List<Square> getRank() {
        return List.copyOf(rank);
    }
}
