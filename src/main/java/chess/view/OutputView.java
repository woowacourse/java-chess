package chess.view;

import java.util.List;
import chess.domain.Board;

public class OutputView {

    private final Converter converter = new Converter();

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(Board board) {
        converter.convertToViewData(board)
                .forEach(this::printRow);
    }

    private void printRow(List<String> rowData) {
        StringBuilder stringBuilder = new StringBuilder();
        rowData.forEach(stringBuilder::append);
        System.out.println(stringBuilder);
    }
}
