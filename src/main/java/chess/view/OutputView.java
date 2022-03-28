package chess.view;

import chess.controller.BoardDto;
import chess.controller.ScoresDto;
import java.util.Map;

public final class OutputView {

    private static final String START_MESSAGE = "체스 게임을 시작합니다.";
    private static final int PIECE_COUNT_PER_RANK = 8;
    private static final String PIECE_DELIMITER = "";
    private static final String NAME_SCORE_DELIMITER = " : ";
    private static final String GAME_RESULT = "승부 결과 : %s\n";
    private static final String ERROR = "[ERROR]]";

    public static void startGame() {
        System.out.println(START_MESSAGE);
    }

    public static void startGameBoard(final BoardDto boardDto) {
        String joinedPieces = String.join(PIECE_DELIMITER, boardDto.getPieces());
        for (int i = PIECE_COUNT_PER_RANK; i <= joinedPieces.length(); i += PIECE_COUNT_PER_RANK) {
            System.out.println(joinedPieces.substring(i - PIECE_COUNT_PER_RANK, i));
        }
    }

    public static void printStatus(ScoresDto dto) {
        for (String name : dto.getScores().keySet()) {
            System.out.println(name + NAME_SCORE_DELIMITER + dto.getScores().get(name));
        }
        System.out.println();
        System.out.printf(GAME_RESULT, dto.getWinner());
    }

    public static void printException(IllegalArgumentException exception) {
        System.out.println(ERROR +exception.getMessage());
    }
}
