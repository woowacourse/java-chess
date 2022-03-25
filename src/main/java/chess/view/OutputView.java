package chess.view;

import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {

    public void printIntroduction() {
        System.out.println("체스 게임을 시작합니다.\n" +
                "게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(Map<Point, Piece> board, Color turnColor) {
        for (int i = LineNumber.MAX; i >= LineNumber.MIN; i--) {
            printBoardLine(board, i);
        }
        System.out.println("현재 턴 : " + turnColor);
    }

    private void printBoardLine(Map<Point, Piece> board, int i) {
        for (int j = LineNumber.MIN; j <= LineNumber.MAX; j++) {
            Point point = Point.of(j, i);
            Piece piece = board.get(point);
            System.out.print(PieceRepresentation.convertType(piece));
        }
        System.out.println();
    }

    public void printException(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printEnd() {
        System.out.println("게임이 종료되었습니다.");
    }
}
