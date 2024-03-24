package view;

import dto.BoardResponse;
import dto.RankResponse;

public class OutputView {
    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public static void printChessBoard(final BoardResponse rankRespons) {
        for (final RankResponse rankResponse : rankRespons.value()) {
            System.out.println(String.join("", rankResponse.value()));
        }
    }

    public static void printErrorMessage(final String message) {
        System.err.println(message);
    }
}
