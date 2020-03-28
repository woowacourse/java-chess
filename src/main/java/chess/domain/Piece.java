package chess.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    /*
     * 개별 말(킹 ... 폰) 에 대한 추상 클래스
     * 각 말 별로 특징 : 점수
     *
     *
     */


    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    Square getMovedSquareByFileAndRank(Square square, int fileIncrementBy, int rankIncrementBy) {
        if (Square.hasCacheAdded(square, fileIncrementBy, rankIncrementBy)) {
            return Square.of(square, fileIncrementBy, rankIncrementBy);
        }
        return square;
    }

    void validateNotNull(Square square, Map<Square, Piece> board) {
        if (Objects.isNull(square) || Objects.isNull(board)) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    // 각 말의 특징에 따라 기본적인 이동 가능 범위를 리턴하도록 구현
    public abstract Set<Square> calculateScope(Square square);

    // 보드 상황을 전달받고, calculateScope 한 후에 최종 이동 가능 범위를 리턴하도록 구현
    public abstract Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board);

    //각 말에 따른 타입 반환하도록 구현
    public abstract double getScore();

    //각 말 별 타입에 맞게
    public abstract String getLetter();

    //
    public abstract void removeSquareIfSameColor(Map<Square, Piece> board, Set<Square> squares, Square pawnScopeSquare);

    void removeSquareWhenSameColor(Map<Square, Piece> board, Set<Square> squares, Square square) {
        if (board.containsKey(square) && board.get(square).color == color) {
            squares.remove(square);
        }
    }

    void findCanNotMovableSquaresInDiagonal(Set<Square> squares, Square s, Map<String, Integer> fileRankDifference) {
        if (fileRankDifference.get("file") > 0 && fileRankDifference.get("rank") > 0) {
            squares.removeAll(findSquaresToRemove(s, 1, 1));
        }
        if (fileRankDifference.get("file") > 0 && fileRankDifference.get("rank") < 0) {
            squares.removeAll(findSquaresToRemove(s, 1, -1));
        }
        if (fileRankDifference.get("file") < 0 && fileRankDifference.get("rank") > 0) {
            squares.removeAll(findSquaresToRemove(s, -1, 1));
        }
        if (fileRankDifference.get("file") < 0 && fileRankDifference.get("rank") < 0) {
            squares.removeAll(findSquaresToRemove(s, -1, -1));
        }
    }

    void findCanNotMovableSquaresInNorthAndSouth(Set<Square> squares, Square s, Map<String, Integer> fileRankDifference) {
        if (fileRankDifference.get("file") == 0 && fileRankDifference.get("rank") > 0) {
            squares.removeAll(findSquaresToRemove(s, 0, 1));
        }
        if (fileRankDifference.get("file") > 0 && fileRankDifference.get("rank") == 0) {
            squares.removeAll(findSquaresToRemove(s, 1, 0));
        }
        if (fileRankDifference.get("file") < 0 && fileRankDifference.get("rank") == 0) {
            squares.removeAll(findSquaresToRemove(s, -1, 0));
        }
        if (fileRankDifference.get("file") == 0 && fileRankDifference.get("rank") < 0) {
            squares.removeAll(findSquaresToRemove(s, 0, -1));
        }
    }

    private Set<Square> findSquaresToRemove(Square s, int fileAddAmount, int rankAddAmount) {
        Set<Square> squaresToRemove = new HashSet<>();
        int file = 0;
        int rank = 0;
        for (int i = 0; i < 8; i++, file += fileAddAmount, rank += rankAddAmount) {
            squaresToRemove.add(getMovedSquareByFileAndRank(s, file, rank));
        }
        squaresToRemove.remove(s);
        return squaresToRemove;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }


}