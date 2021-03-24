package chess.view;

import chess.controller.dto.response.BoardStatusResponseDTO;
import chess.controller.dto.response.ScoresResponseDTO;
import java.util.List;

public class OutputView {
    private static final int BOARD_ALL_CELLS_SIZE = 64;
    private static final int BOARD_WIDTH_SIZE = 8;

    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(BoardStatusResponseDTO boardStatusResponseDTO) {
        List<String> cellsStatus = boardStatusResponseDTO.getCellsStatus();
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
        System.out.println();
    }

    public static void printWinnerTeamColor(String winnerTeamColorKoreanName) {
        System.out.println(winnerTeamColorKoreanName + " 팀이 이겼습니다.");
    }
}
