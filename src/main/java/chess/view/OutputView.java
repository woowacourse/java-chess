package chess.view;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Color;
import chess.dto.BoardViewDto;
import chess.dto.GameResultDto;

public class OutputView {

    private static final String BLANK_LINE = System.lineSeparator();

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String GAME_OVER_MESSAGE = "> 게임이 종료되었습니다!";
    private static final String START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String END_COMMAND_MESSAGE = "> 프로그램 종료 : end";
    private static final String MOVE_COMMAND_MESSAGE = "> 체스 말 이동 : move source target (예. move b2 b3)";
    private static final String STATUS_COMMAND_MESSAGE = "> 게임 결과 조회 : status";

    private static final String WINNER_ANNOUNCEMENT_FORMAT = "%s 플레이어가 승리하였습니다!" + BLANK_LINE;
    private static final String SCORE_DISPLAY_FORMAT = "%s 플레이어 점수 : %2.1f점" + BLANK_LINE;

    public void printGameInstructions() {
        String instructionMessage = GAME_START_MESSAGE + BLANK_LINE
                + START_COMMAND_MESSAGE + BLANK_LINE
                + END_COMMAND_MESSAGE + BLANK_LINE
                + MOVE_COMMAND_MESSAGE;

        print(instructionMessage);
    }

    public void printBoard(BoardViewDto dto) {
        StringBuilder builder = new StringBuilder();
        for (String rowDisplay : dto.boardDisplay()) {
            builder.append(rowDisplay)
                    .append(BLANK_LINE);
        }
        print(builder.toString());
    }

    public void printGameOverInstructions() {
        String instructionMessage = GAME_OVER_MESSAGE + BLANK_LINE
                + STATUS_COMMAND_MESSAGE + BLANK_LINE
                + END_COMMAND_MESSAGE + BLANK_LINE;

        print(instructionMessage);
    }

    public void printStatus(GameResultDto dto) {
        Color winnerColor = dto.winnerColor();
        double whiteScore = dto.whiteScore();
        double blackScore = dto.blackScore();

        String statusText = String.format(WINNER_ANNOUNCEMENT_FORMAT, winnerColor)
                + String.format(SCORE_DISPLAY_FORMAT, WHITE, whiteScore)
                + String.format(SCORE_DISPLAY_FORMAT, BLACK, blackScore);

        print(statusText);
    }

    public static void print(String value) {
        System.out.println(value);
    }
}
