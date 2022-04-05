package chess.console.view;

import chess.console.service.GameResult;
import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String START_MESSAGE = "체스 게임을 시작합니다.";
    private static final int PIECE_COUNT_PER_RANK = 8;
    private static final String PIECE_DELIMITER = "";
    private static final String NAME_SCORE_DELIMITER = " : ";
    private static final String GAME_RESULT = "승부 결과 : %s\n";
    private static final String ERROR = "[ERROR]";
    private static final String END_MESSAGE = "게임을 종료합니다. 안녕히 가십시오.";
    private static final String BLACK_PLAYER_NAME = "BLACK";
    private static final String WHITE_PLAYER_NAME = "WHITE";
    private static final String WIN = " 승리";
    private static final String DRAW = "무승부";

    public static void startGame() {
        System.out.println(START_MESSAGE);
    }

    public static void printBoard(final List<List<String>> pieceLetters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<String> lettersInRank : pieceLetters) {
            appendLetters(stringBuilder, lettersInRank);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void appendLetters(StringBuilder stringBuilder, List<String> lettersInRank) {
        for (String letter : lettersInRank) {
            stringBuilder.append(letter);
        }
    }

    public static void printStatus(Map<String, Double> scores) {
        for (String name : scores.keySet()) {
            System.out.println(name + NAME_SCORE_DELIMITER + scores.get(name));
        }
        System.out.println();
    }

    public static void printException(RuntimeException exception) {
        System.out.println(ERROR + exception.getMessage());
    }

    public static void printEndMessage() {
        System.out.println(END_MESSAGE);
    }

    public static void printWinner(GameResult result) {
        if(result.getIsDraw()) {
            System.out.println("비겼습니다!");
            return;
        }
        System.out.println(String.format("승자는 %s입니다!", result.getWinnerColor()));
    }
}
