package chess.beforedb.view;

import chess.db.controller.dto.response.BoardResponseDTOForDB;
import chess.db.controller.dto.response.MoveResponseDTOForDB;
import chess.db.controller.dto.response.ResponseDTOForDB;
import java.util.ArrayList;
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

    public static void printBoard(ResponseDTOForDB responseDTO) {
        List<String> cellsStatus = getParsedCellsStatus(responseDTO);
        for (int cellIndex = 0; cellIndex < BOARD_ALL_CELLS_SIZE; cellIndex++) {
            printCellStatus(cellsStatus, cellIndex);
        }
        printCurrentTurnTeamName(responseDTO);
        printWinnerTeamNameWhenKingDead(responseDTO);
    }

    private static void printCurrentTurnTeamName(ResponseDTOForDB responseDTO) {
        System.out.println();
        if (!responseDTO.isEnd() && !responseDTO.getIsKingDead()) {
            System.out.println("현재 " + responseDTO.getCurrentTurnTeamName() + " 팀의 차례입니다.");
        }
    }

    private static void printWinnerTeamNameWhenKingDead(ResponseDTOForDB responseDTO) {
        if (responseDTO.getIsKingDead()) {
            System.out.println(responseDTO.getBeforeTurnTeamName() + " 팀이 이겼습니다.");
        }
    }

    private static List<String> getParsedCellsStatus(ResponseDTOForDB responseDTO) {
        BoardResponseDTOForDB boardResponseDTO = responseDTO.getBoardResponseDTO();
        List<String> cellsStatus = new ArrayList<>(boardResponseDTO.getRank8());
        cellsStatus.addAll(boardResponseDTO.getRank7());
        cellsStatus.addAll(boardResponseDTO.getRank6());
        cellsStatus.addAll(boardResponseDTO.getRank5());
        cellsStatus.addAll(boardResponseDTO.getRank4());
        cellsStatus.addAll(boardResponseDTO.getRank3());
        cellsStatus.addAll(boardResponseDTO.getRank2());
        cellsStatus.addAll(boardResponseDTO.getRank1());
        return cellsStatus;
    }

    private static void printCellStatus(List<String> cellsStatus, int cellIndex) {
        System.out.print(cellsStatus.get(cellIndex));
        int cellOrder = cellIndex + 1;
        if (cellOrder % BOARD_WIDTH_SIZE == 0) {
            System.out.println();
        }
    }

    public static void printScores(ResponseDTOForDB responseDTO) {
        double blackTeamScore = responseDTO.getBlackPlayerScore();
        double whiteTeamScore = responseDTO.getWhitePlayerScore();
        System.out.printf("흑 팀 점수 : %.1f, 백 팀 점수 : %.1f\n", blackTeamScore, whiteTeamScore);
    }

    public static void printErrorMessage(MoveResponseDTOForDB moveResponse) {
        System.out.println("에러 : " + moveResponse.getErrorMessage());
    }
}
