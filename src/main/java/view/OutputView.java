package view;

import dto.BoardResponse;
import dto.GameResultResponse;
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

    public static void printChessBoard(final BoardResponse rankResponses) {
        for (final RankResponse rankResponse : rankResponses.value()) {
            System.out.println(String.join("", rankResponse.value()));
        }
    }

    public static void printErrorMessage(final String message) {
        System.err.println(message);
    }

    public static void printChessResult(final GameResultResponse gameResultResponse) {
        System.out.println("흰색 팀 점수:" + gameResultResponse.whiteScore());
        System.out.println("검은색 팀 점수:" + gameResultResponse.blackScore());
    }
}
