package chess.view;

import chess.domain.gamestatus.GameStatus;

import java.util.List;

public class OutputView {
    private static final String ENTER = System.lineSeparator();

    public static void printStartInformation() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(GameStatus gameStatus) {
        for (List<String> raw : gameStatus.getBoard()) {
            for (String acronym : raw) {
                System.out.print(acronym);
            }
            System.out.println();
        }
    }

    public static void printExceptionMessage(RuntimeException e) {
        System.err.println(e.getMessage());
    }

    public static void printScoreResult(GameStatus gameStatus) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("White 점수 : ")
                .append(gameStatus.getWhiteTeamScore())
                .append(ENTER)
                .append("Black 점수 : ")
                .append(gameStatus.getBlackTeamScore());

        System.out.println(stringBuilder.toString());
    }
}
