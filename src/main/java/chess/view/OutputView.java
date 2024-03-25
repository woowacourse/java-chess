package chess.view;

import chess.domain.board.Board;
import java.util.List;

public class OutputView {

    private final BoardConverter boardConverter = new BoardConverter();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Board board) {
        boardConverter.convertToViewData(board)
                .forEach(this::printRow);
        System.out.println();
    }

    private void printRow(List<String> rowData) {
        StringBuilder stringBuilder = new StringBuilder();
        rowData.forEach(stringBuilder::append);
        System.out.println(stringBuilder);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
