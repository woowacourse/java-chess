package chess.view;

import java.util.List;

public final class OutputView {

    private OutputView(){}

    public static void printWelcomeMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printChessBoard(List<List<String>> chessBoard) {
        for (int i = 0; i < chessBoard.size(); i++) {
            printEachRank(chessBoard.get(i), chessBoard.size() - i);
        }
        System.out.println(System.lineSeparator() + "abcdefgh");
    }

    private static void printEachRank(final List<String> rank, int rankCount) {
        for (String square : rank) {
            System.out.print(square);
        }
        System.out.println("  " + rankCount);
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
