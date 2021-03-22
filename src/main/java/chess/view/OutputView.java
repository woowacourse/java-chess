package chess.view;

import static chess.controller.Application.WHITE_TEAM_COLOR;

import chess.controller.dto.response.BoardResponseDTO;
import chess.controller.dto.response.ScoresResponseDTO;
import java.util.List;

public class OutputView {
    private static final int BOARD_ALL_CELLS_SIZE = 64;
    private static final int BOARD_WIDTH_SIZE = 8;
    private static final String WHITE_TEAM_COLOR_KOREAN = "백";
    private static final String BLACK_TEAM_COLOR_KOREAN = "흑";

    private OutputView() {
    }

    public static void printBoard(BoardResponseDTO boardResponseDTO) {
        List<String> cellsStatus = boardResponseDTO.getCellsStatus();
        for (int cellIndex = 0; cellIndex < BOARD_ALL_CELLS_SIZE; cellIndex++) {
            printCellStatus(cellsStatus, cellIndex);
        }
        System.out.println();
    }

    private static void printCellStatus(List<String> cellsStatus, int cellIndex) {
        System.out.print(cellsStatus.get(cellIndex));
        int cellOrder = cellIndex + 1;
        if (cellOrder % BOARD_WIDTH_SIZE == 0) {
            System.out.println();
        }
    }

    public static void printScores(ScoresResponseDTO scoresResponseDTO) {
        double blackTeamScore = scoresResponseDTO.getBlackTeamScore();
        double whiteTeamScore = scoresResponseDTO.getWhiteTeamScore();
        System.out.printf("흑 팀 점수 : %.1f, 백 팀 점수 : %.1f\n", blackTeamScore, whiteTeamScore);
    }

    public static void printWinnerTeamColor(String winnerTeamColor) {
        System.out.println(getKoreanTeamColorName(winnerTeamColor) + " 팀이 이겼습니다.");
    }

    private static String getKoreanTeamColorName(String teamColor) {
        if (teamColor.equals(WHITE_TEAM_COLOR)) {
            return WHITE_TEAM_COLOR_KOREAN;
        }
        return BLACK_TEAM_COLOR_KOREAN;
    }
}
