package chess.view;

import chess.domain.gamestate.GameState;

import java.util.List;

public class OutputView {
    private static final String ENTER = System.lineSeparator();

    public static void printStartInformation() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(GameState gameState) {
        for (List<String> raw : gameState.getBoard()) {
            for (String acronym : raw) {
                System.out.print(acronym);
            }
            System.out.println();
        }
    }

    public static void printExceptionMessage(RuntimeException e) {
        System.err.println(e.getMessage());
    }

    public static void printScoreResult(GameState gameState) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("White 점수 : ")
                .append(gameState.getWhiteTeamScore())
                .append(ENTER)
                .append("Black 점수 : ")
                .append(gameState.getBlackTeamScore());

        System.out.println(stringBuilder.toString());
    }
}
