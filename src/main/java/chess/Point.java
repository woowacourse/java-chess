package chess;

public class Point {
    private static final int ASCII_CODE_GAP = 49;
    private static final int RANK_GAP = 8;

    private final int x;
    private final int y;

    public Point(char a, int i) {
        x = convertLetterToIndex(a);
        y = convertRankToIndex(i);
    }

    private int convertRankToIndex(int i) {
        return Math.abs(i - RANK_GAP);
    }

    private int convertLetterToIndex(char a) {
        return (int) a - ASCII_CODE_GAP;
    }
}
