package view;

import dto.RankInfo;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(final List<RankInfo> rankInfo) {
        for (final RankInfo info : rankInfo) {
            for (final String piece : info.piecesOfRank()) {
                System.out.print(piece);
            }
            System.out.println();
        }
        System.out.println();
    }
}
