package chess.view;

import chess.controller.ChessBoardFormatter;

import java.util.List;

public final class OutputView {

    private OutputView() {
    }

    public static void printWelcomeMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작: " + Command.START.value());
        System.out.println("게임 종료: " + Command.END.value());
        System.out.println("게임 이동: move source target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final ChessBoardFormatter chessBoardMarkConverter) {
        List<String> chessBoard = chessBoardMarkConverter.getChessBoardMark();

        for (int i = 0; i < chessBoard.size(); i++) {
            int rankCount = chessBoard.size() - i;
            System.out.println(chessBoard.get(i) + "  |" + rankCount);
        }
        System.out.println("________");
        System.out.println("abcdefgh");
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println("[ERROR] " + errorMessage + System.lineSeparator());
    }
}
