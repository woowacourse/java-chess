package view;

import view.dto.RankInfo;
import view.dto.RankInfos;

public class OutputView {
    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final RankInfos ranks) {
        for (final RankInfo rank : ranks.getRankInfos()) {
            printRank(rank);
            System.out.println();
        }
        System.out.println();
    }

    private static void printRank(final RankInfo rank) {
        for (final String piece : rank.getPieces()) {
            System.out.print(piece);
        }
    }

    public static void printErrorMessage(final String message) {
        System.err.println(message);
        System.out.println();
    }
}
