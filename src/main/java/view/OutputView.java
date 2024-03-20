package view;

import coordinate.Coordinate;
import java.util.Map;
import piece.Piece;
import position.Column;
import position.Row;
import view.util.PieceTranslator;

public class OutputView {

    public void printInfo() {
        System.out.print("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3
                """);
    }

    public void printBoard(Map<Coordinate, Piece> board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinate coordinate = new Coordinate(new Row(i), new Column(j));
                Piece piece = board.get(coordinate);
                System.out.print(PieceTranslator.getName(piece));
            }
            System.out.println();
        }
    }
}
