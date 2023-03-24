package view;

import dto.ChessBoardDto;

import java.util.List;

public final class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 점수 : status");
        System.out.println();
    }

    public static void printNotice(final String message) {
        System.out.println(System.lineSeparator() + message);
    }

    public static void printErrorMessage(final String message) {
        System.out.printf("[ERROR] : %s%n", message);
    }

    public static void printChessBoard(final ChessBoardDto chessBoard) {
        final List<List<String>> rowDTOs = chessBoard.getRowDTOs();

        System.out.print(System.lineSeparator());
        final int size = rowDTOs.size();
        for (int i = 0; i < size; i++) {
            final List<String> row = rowDTOs.get(i);
            printRow(row);
            System.out.printf("  %d%n", 8 - i);
        }
        System.out.println(System.lineSeparator() + "abcdefgh" + System.lineSeparator());
    }

    private static void printRow(final List<String> chessBoardElements) {
        for (String chessBoardElement : chessBoardElements) {
            System.out.print(chessBoardElement);
        }
    }

    public static void printScore(final double whiteScore, final double blackScore) {
        final String result = findWinner(whiteScore, blackScore);

        System.out.printf("White : %.1f%n", whiteScore);
        System.out.printf("Black : %.1f%n", blackScore);
        System.out.println(result);
    }

    private static String findWinner(final double whiteScore, final double blackScore) {
        if (whiteScore > blackScore) {
            return "White Win";
        }
        if (whiteScore == blackScore) {
            return "Draw";
        }
        return "Black Win";
    }


}
