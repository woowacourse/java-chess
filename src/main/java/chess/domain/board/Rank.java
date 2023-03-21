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
    
    public void validatePiece(final int column) {
        if (isEmptyPiece(column)) {
            return;
        }
        throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다. 다시 입력해주세요.");
    }

    public boolean isEmptyPiece(final int column) {
        return findSquare(column).isEmptyPiece();
    }

    public Square findSquare(final int column) {
        return rank.get(column);
    }

    public void replaceSquare(final int column, final Square square) {
        rank.set(column, square);
    }

    public void replacePiece(final int column, final Piece newPiece) {
        Square square = findSquare(column);
        Square replacedSquare = square.replacePiece(newPiece);
        replaceSquare(column, replacedSquare);
    }

    public Piece findPiece(final int column) {
        Square square = findSquare(column);
        return square.getPiece();
    }

    public List<Square> getRank() {
        return List.copyOf(rank);
    }
}
