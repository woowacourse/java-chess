package chess.view;

import chess.domain.board.Board;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public static void printInputStartGuideMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printInitializedBoard(Board board) {
        int count = 1;
        for (int index = 0; index < 64; index++) {
            System.out.print(board.getPiece(index));
            if (count % 8 == 0) {
                System.out.print(NEW_LINE);
            }
            count++;
        }
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

}
