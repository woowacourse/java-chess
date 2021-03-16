package chess;

public class Point {
    private static final int ASCII_CODE_GAP = 49;
    private static final int RANK_GAP = 8;
    private static final char MINIMUM_LETTER = 'a';
    private static final char MAXIMUM_LETTER = 'h';
    private static final int MINIMUM_RANK = 1;
    private static final int MAXIMUM_RANK = 8;

    private final int x;
    private final int y;

    public Point(char letter, int rank) {
        x = convertLetterToIndex(letter);
        y = convertRankToIndex(rank);
    }

    private int convertLetterToIndex(char letter) {
        validateLetter(letter);
        return (int) letter - ASCII_CODE_GAP;
    }

    private void validateLetter(char letter) {
        if (letter < MINIMUM_LETTER || MAXIMUM_LETTER < letter){
            throw new IllegalArgumentException("옳지 않은 열 입력입니다.");
        }
    }

    private int convertRankToIndex(int rank) {
        validateRank(rank);
        return Math.abs(rank - RANK_GAP);
    }

    private void validateRank(int rank) {
        if (rank < MINIMUM_RANK || MAXIMUM_RANK < rank){
            throw new IllegalArgumentException("옳지 않은 행 입력입니다.");
        }
    }
}
