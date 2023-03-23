package chess.view;

import chess.domain.Team;
import chess.dto.SquareResponse;

import java.util.List;

public class OutputView {

    private static final int CRITERIA_Y_POS = 7;
    private static final int BOARD_SIZE = 8;
    private static final String ROW_DELIMITER = "";
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String COMMAND_INPUT_MESSAGE = "> 게임 시작 : start" + LINE_SEPARATOR +
            "> 게임 종료 : end" + LINE_SEPARATOR +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + LINE_SEPARATOR +
            "> 점수 확인 : status";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println(COMMAND_INPUT_MESSAGE);
    }

    public static void printBoard(List<SquareResponse> responses) {
        String[][] board = convertResponseToBoard(responses);
        for (String[] row : board) {
            System.out.println(String.join(ROW_DELIMITER, row));
        }
    }

    public static void printScore(double whiteTeamScore, double blackTeamScore) {
        System.out.println("WHITE: " + whiteTeamScore);
        System.out.println("BLACK: " + blackTeamScore);
    }

    public static void printEndMessage(Team winningTeam, Team losingTeam) {
        System.out.println(losingTeam.name() + "팀의 왕이 죽었습니다.");
        System.out.println(winningTeam.name() + "팀이 게임에서 승리했습니다.");
    }

    private static String[][] convertResponseToBoard(List<SquareResponse> responses) {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

        for (SquareResponse response : responses) {
            int x = response.getX();
            int y = convertYPos(response.getY());
            String symbol = response.getSymbol();
            board[y][x] = symbol;
        }
        return board;
    }

    private static int convertYPos(int yPos) {
        return CRITERIA_Y_POS - yPos;
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
