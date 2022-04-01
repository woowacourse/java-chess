package chess.view;

import chess.domain.ChessBoardPosition;
import chess.dto.ChessBoardDto;
import chess.dto.ChessStatusDto;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final int FIRST_ROW_INDEX = 1;
    private static final int LAST_ROW_INDEX = 8;
    private static final char FIRST_COLUMN_INDEX = 'a';
    private static final char LAST_COLUMN_INDEX = 'h';
    private static final char EMPTY_CHESS_BLOCK = '.';
    private static final String CHESS_GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String GAME_START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_COMMAND_MESSAGE = "> 게임 종료 : end";
    private static final String GAME_MOVE_COMMAND_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String GAME_STATUS_COMMAND_MESSAGE = "> 게임 각 진영의 점수 확인 : status";
    private static final String WINNER_FORMAT = "우승 팀: %s\n";
    private static final String TEAM_SCORE_DELIMITER = ": ";
    private static final String IN_GAME_COMMAND_EXCEPTION = "[ERROR] move, status 중 하나를 입력해주세요.";

    private OutputView() {
    }

    public static void printChessGameStart() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CHESS_GAME_START_MESSAGE)
                .append(System.lineSeparator())
                .append(GAME_START_COMMAND_MESSAGE)
                .append(System.lineSeparator())
                .append(GAME_END_COMMAND_MESSAGE)
                .append(System.lineSeparator())
                .append(GAME_MOVE_COMMAND_MESSAGE)
                .append(System.lineSeparator())
                .append(GAME_STATUS_COMMAND_MESSAGE);
        System.out.println(stringBuilder);
    }

    public static void printCurrentChessBoard(ChessBoardDto chessBoardDto) {
        Map<ChessBoardPosition, Character> board = chessBoardDto.getBoardDto();
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = LAST_ROW_INDEX; row >= FIRST_ROW_INDEX; row--) {
            stringBuilder.append(makeRow(board, row))
                    .append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private static String makeRow(Map<ChessBoardPosition, Character> board, int row) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char column = FIRST_COLUMN_INDEX; column <= LAST_COLUMN_INDEX; column++) {
            ChessBoardPosition position = ChessBoardPosition.of(column, row);
            stringBuilder.append(board.getOrDefault(position, EMPTY_CHESS_BLOCK));
        }
        return stringBuilder.toString();
    }

    public static void printStatus(ChessStatusDto chessStatusDto) {
        for (Entry<String, Double> entry : chessStatusDto.getTeamScore().entrySet()) {
            System.out.println(entry.getKey() + TEAM_SCORE_DELIMITER + entry.getValue());
        }
        System.out.printf(WINNER_FORMAT, chessStatusDto.getWinner());
    }

    public static void printInGameCommandExceptionMessage() {
        System.out.println(IN_GAME_COMMAND_EXCEPTION);
    }

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
