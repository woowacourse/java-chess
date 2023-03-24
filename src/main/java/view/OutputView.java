package view;

import domain.piece.Piece;
import domain.position.Position;

import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" +
            "> 게임 종료 : end \n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printChessBoard(List<Position> allPosition, Map<Position, Piece> chessBoard) {
        System.out.println();
        int lineBreak = 0;
        for (Position position : allPosition) {
            lineBreak++;
            printPieceOrDot(chessBoard, position);
            lineBreakByRow(lineBreak);
        }
        System.out.println();
    }

    private static void lineBreakByRow(int lineBreak) {
        if (lineBreak % 8 == 0)
            System.out.println();
    }

    private static void printPieceOrDot(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            System.out.print(chessBoard.get(position).getName());
            return;
        }
        System.out.print(".");
    }

    public static void printNotStartedGameMessage() {
        System.out.println("아직 게임이 시작되지 않았습니다.");
    }
}
