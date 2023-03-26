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
        System.out.println("게임 현황: " + Command.STATUS.value());
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

    public static void printScore(final String team, final double score) {
        System.out.println(team + "의 점수는 " + score + "점 입니다.");
    }

    public static void printDrawWhenRunning() {
        System.out.println("두 플레이어가 동등하게 겨루고 있습니다.");
    }

    public static void printWinnerWhenRunning(final String winner) {
        System.out.println(winner + "이(가) 우세합니다.");
    }

    public static void printWinnerAfterRunning(final String winner) {
        System.out.println(winner + "이(가) 상태팀의 왕을 잡아 승리하였습니다.");
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println("[ERROR] " + errorMessage + System.lineSeparator());
    }
}
