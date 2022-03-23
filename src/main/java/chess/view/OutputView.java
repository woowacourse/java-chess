package chess.view;

import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {

    public void printIntroduction() {
        System.out.println("체스 게임을 시작합니다.\n" +
                "게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(Map<Point, Piece> board) {
        for (int i = LineNumber.MIN; i <= LineNumber.MAX; i++) {
            printBoardLine(board, i);
        }
    }

    private void printBoardLine(Map<Point, Piece> board, int i) {
        for (int j = LineNumber.MIN; j <= LineNumber.MAX; j++) {
            Point point = Point.of(i, j);
            Piece piece = board.get(point);
            System.out.print(PieceRepresentation.convertType(piece));
        }
        System.out.println();
    }
}
