package chess.view;

import chess.domain.pieceInfo.Team;
import chess.domain.dto.BoardDto;
import java.util.List;

public class OutputView {

    public static void printBoard(final BoardDto boardDto) {
        List<List<String>> rawBoard = boardDto.getBoard();

        for (int i = 7; i >= 0; i--) {
            rawBoardRow(rawBoard, i);
        }
        System.out.println();
    }

    private static void rawBoardRow(final List<List<String>> rawBoard, final int index) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < 8; j++) {
            stringBuilder.append(rawBoard.get(index).get(j));
        }
        System.out.println(stringBuilder);
    }

    public static void printChessGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
    }

    public static void printCommandGuideMessage() {
        System.out.printf("> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n"
                + "> 게임 점수 : status%n");
    }

    public static void printWinner(final Team turn) {
        System.out.printf("%s 진영이 승리했습니다!%n", turn.name());
    }

    public static void printScoreWithWinner(final double whiteScore, final double blackScore, final Team team) {
        System.out.printf("> %s 점수 : %f%n", Team.WHITE, whiteScore);
        System.out.printf("> %s 점수 : %f%n", Team.BLACK, blackScore);
        System.out.printf("> %s 진영이 우승하였습니다!%n", team);
    }

    public static void printScoreWithDraw(final double whiteScore, final double blackScore) {
        System.out.printf("> %s 점수 : %f%n", Team.WHITE, whiteScore);
        System.out.printf("> %s 점수 : %f%n", Team.BLACK, blackScore);
        System.out.printf("> 두 진영이 같은 점수입니다.%n");
    }
}
