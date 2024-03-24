package view;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPiece;
import domain.position.Column;
import domain.position.Row;
import java.util.Map;
import view.util.PieceTranslator;

public class OutputView {

    private static final int CHESS_BOARD_SIZE = 9;

    public void printGameGuide() {
        System.out.print("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3
                """);
    }

    public void printBoard(Map<Coordinate, ChessPiece> board) {
        for (int row = 8; row > 0; row--) {
            printRow(board, row);
            System.out.println();
        }
    }

    private void printRow(Map<Coordinate, ChessPiece> board, int row) {
        for (int column = 1; column < CHESS_BOARD_SIZE; column++) {
            Coordinate coordinate = new Coordinate(new Row(row), new Column(column));
            ChessPiece piece = board.get(coordinate);
            System.out.print(PieceTranslator.getName(piece));
        }
    }
}
