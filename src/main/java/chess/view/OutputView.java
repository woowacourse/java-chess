package chess.view;

import chess.domain.piece.Team;
import chess.dto.BoardDto;

public final class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final BoardDto boardDto) {
        int count = 0;
        for (String name : boardDto.getNames()) {
            System.out.print(name);
            count++;
            if (count % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void printWinner(final Team winner) {
        System.out.printf("왕이 죽었습니다. 승자는 %s팀 입니다.", winner.name());
    }

    public void printEndMessage() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public void printErrorMessage(final String exceptionMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + exceptionMessage);
    }
}
