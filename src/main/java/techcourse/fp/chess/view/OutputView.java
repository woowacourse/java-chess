package techcourse.fp.chess.view;

import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.dto.BoardResponse;

public final class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final BoardResponse boardResponse) {
        int count = 0;

        for (String name : boardResponse.getNames()) {
            System.out.print(name);
            count++;
            if (count % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void printEndMessage() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public void printErrorMessage(final String exceptionMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + exceptionMessage);
    }

    public void printWinningMessage(final Color winner) {
        System.out.println(winner +" 이(가) 승리하였습니다!");
    }
}
