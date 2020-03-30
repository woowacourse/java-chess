package view;


import domain.pieces.Pieces;
import domain.point.Point;

public class OutputView {

    private static final int INITIAL_ZERO = 0;
    private static final int NEXT_LINE_COUNT = 8;

    public static void printStart() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printBoard(Pieces pieces) {
        int newLineCount = INITIAL_ZERO;
        for (Point point : pieces.getPieces().keySet()) {
            printRowBoard(pieces, point);
            newLineCount++;
            newLineCount = getKeepOrInitialCount(newLineCount);
        }
        System.out.println();
    }

    private static int getKeepOrInitialCount(int newLineCount) {
        if (newLineCount == NEXT_LINE_COUNT) {
            newLineCount = INITIAL_ZERO;
            System.out.println();
        }
        return newLineCount;
    }

    private static void printRowBoard(Pieces pieces, Point point) {
        if (pieces.isExistPiece(point)) {
            System.out.print(pieces.getPiece(point).toString());
            return;
        }
        System.out.print(".");
    }

    public static void printScore(double score) {
        System.out.println(score + "점");
    }
}
