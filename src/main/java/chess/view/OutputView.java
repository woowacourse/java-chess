package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final int END_INDEX = 8;
    private static final int START_INDEX = 1;

    public static void printInputStartGuideMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(List<Piece> board) {
        for (int row = END_INDEX; row >= START_INDEX; row--) {
            for (int col = START_INDEX; col <= END_INDEX; col++) {
                System.out.print(board.get((row - 1) * Position.ROW_SIZE + col - 1));
            }
            System.out.print(NEW_LINE);
        }
    }

    public static void printGameFinish() {
        System.out.println("왕이 잡혀서 게임이 종료되었습니다.");
    }

    public static void printTeamScore(double whiteScore, double blackScore) {
        System.out.println(String.format("흰색 팀의 점수는 %.1f, 검은 색 팀의 점수는 %.1f 입니다.", whiteScore, blackScore));
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
