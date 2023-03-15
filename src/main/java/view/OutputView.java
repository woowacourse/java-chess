package view;

import domain.piece.Piece;
import domain.position.Position;

import java.util.List;
import java.util.Map;

public final class OutputView {

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printChessBoard(List<Position> allPosition, Map<Position, Piece> chessBoard) {
        System.out.println();
        int lineBreak = 0;
        for (Position position : allPosition) {
            lineBreak++;
            printFigureOrDot(chessBoard, position);
            lineBreakByRow(lineBreak);
        }
        System.out.println();
    }

    private static void lineBreakByRow(int lineBreak) {
        if(lineBreak % 8 == 0)
            System.out.println();
    }

    private static void printFigureOrDot(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            System.out.print(chessBoard.get(position).getName());
            return;
        }
        System.out.print(".");
    }
}
