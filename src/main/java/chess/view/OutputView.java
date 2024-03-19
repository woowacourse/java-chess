package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(Board board) { // TODO DTO 로 바꾸기
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
                    result.get(columnIndex).replace(rowIndex, rowIndex + 1, PieceMapper.findByPieceType(piece));
                });

        result.forEach(System.out::println);
    }
}
