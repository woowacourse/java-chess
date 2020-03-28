package view;


import domain.pieces.Pieces;
import domain.point.Point;

public class OutputView {

    public static void printStart() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printBoard(Pieces pieces) {
        int newLineCount = 0;
        for (Point point : pieces.getPieces().keySet()) {
            printRowBoard(pieces, point);
            newLineCount++;
            if (newLineCount == 8) {
                System.out.println();
                newLineCount = 0;
            }
        }
        System.out.println();
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
