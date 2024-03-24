package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Board board) {
        List<StringBuilder> result = new ArrayList<>();
        result.add(new StringBuilder("........"));
        result.add(new StringBuilder("........"));
        result.add(new StringBuilder("........"));
        result.add(new StringBuilder("........"));
        result.add(new StringBuilder("........"));
        result.add(new StringBuilder("........"));
        result.add(new StringBuilder("........"));
        result.add(new StringBuilder("........"));

        board.getBoard().keySet()
                .forEach(position -> {
                    Piece piece = board.getBoard().get(position);
                    int rowIndex = position.getRowIndex();
                    int columnIndex = position.getColumnIndex();
                    result.get(rowIndex).replace(columnIndex, columnIndex + 1, PieceMapper.findByPieceType(piece));
                });

        result.forEach(System.out::println);
        System.out.println();
    }

    public void printError(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
