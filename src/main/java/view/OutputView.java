package view;

import controller.ChessBoardDTO;

import java.util.List;

public final class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 좌표 안내");
        System.out.println("RNBQKBNR  8 (rank 8)\n" +
                "PPPPPPPP  7\n" +
                "........  6\n" +
                "........  5\n" +
                "........  4\n" +
                "........  3\n" +
                "pppppppp  2\n" +
                "rnbqkbnr  1 (rank 1)\n" +
                "\n" +
                "abcdefgh\n");
    }

    public static void printNotice(final String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(final String message) {
        System.out.printf("[ERROR] : %s%n", message);
    }

    public static void printChessBoard(final ChessBoardDTO chessBoard) {
        final List<List<String>> rowDTOs = chessBoard.getRowDTOs();

        System.out.print(System.lineSeparator());
        for (List<String> row : rowDTOs) {
            printRow(row);
        }
        System.out.print(System.lineSeparator());
    }

    private static void printRow(final List<String> chessBoardElements) {
        for (String chessBoardElement : chessBoardElements) {
            System.out.print(chessBoardElement);
        }
        System.out.println();
    }

}
