package chess.view;

import chess.domain.Team;
import chess.dto.SquareResponse;
import java.util.List;

public class OutputView {
    private static final int CRITERIA_Y_POS = 7;
    private static final int BOARD_SIZE = 8;
    private static final String ROW_DELIMITER = "";
    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String COMMAND_START_MESSAGE = "> 게임 시작 : start";
    private static final String COMMAND_MOVE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String COMMAND_SAVE_MESSAGE = "> 게임 저장 : save";
    private static final String COMMAND_LOAD_MESSAGE = "> 게임 불러오기 : load";
    private static final String COMMAND_LEAVE_MESSAGE = "> 게임 나가기 : leave";
    private static final String COMMAND_STATUS_MESSAGE = "> 현재 점수 : status";
    private static final String COMMAND_END_MESSAGE = "> 게임 종료 : end";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println(COMMAND_START_MESSAGE);
        System.out.println(COMMAND_MOVE_MESSAGE);
        System.out.println(COMMAND_SAVE_MESSAGE);
        System.out.println(COMMAND_LOAD_MESSAGE);
        System.out.println(COMMAND_LEAVE_MESSAGE);
        System.out.println(COMMAND_STATUS_MESSAGE);
        System.out.println(COMMAND_END_MESSAGE);
    }

    public static void printBoard(List<SquareResponse> responses) {
        String[][] board = createBoard(responses);
        for (String[] row : board) {
            System.out.println(String.join(ROW_DELIMITER, row));
        }
        System.out.println();
    }

    private static String[][] createBoard(List<SquareResponse> responses) {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (SquareResponse response : responses) {
            int x = response.getX();
            int y = convertYPos(response.getY());
            board[y][x] = response.getSymbol();
        }
        return board;
    }

    private static int convertYPos(int yPos) {
        return CRITERIA_Y_POS - yPos;
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printGameStatus(double blackTeamScore, double whiteTeamScore) {
        System.out.println("게임 상태");
        System.out.println("Black 팀: " + blackTeamScore + "점");
        System.out.println("White 팀: " + whiteTeamScore + "점");
        System.out.println(getWinnerTeam(blackTeamScore, whiteTeamScore));
        System.out.println();
    }

    private static String getWinnerTeam(double blackTeamScore, double whiteTeamScore) {
        if (blackTeamScore > whiteTeamScore) {
            return "Black 팀 승리";
        }
        if (whiteTeamScore > blackTeamScore) {
            return "White 팀 승리";
        }
        return "무승부";
    }

    public static void printTurn(Team team) {
        System.out.println(getTeamName(team) + " 팀의 차례입니다.");
    }

    private static String getTeamName(Team team) {
        if (team == Team.BLACK) {
            return "Black";
        }
        if (team == Team.WHITE) {
            return "White";
        }
        throw new IllegalArgumentException("[ERROR] 해당 팀은 이름이 없습니다.");
    }

    public static void printWinner(Team winner) {
        if (winner != Team.NONE) {
            System.out.println(getTeamName(winner) + " 팀이 체크메이트로 승리했습니다.");
        }
    }

    public static void printCheckWarning(Team team) {
        System.out.println(getTeamName(team) + " 팀은 체크 상태입니다.");
        System.out.println("이번 턴에 체크 상태를 벗어나지 못하면 체크메이트로 패배합니다.");
    }

    public static void printSaveMessage() {
        System.out.println("진행사항을 저장했습니다.");
    }

    public static void printLoadMessage() {
        System.out.println("진행사항을 불러왔습니다.");
    }
}
