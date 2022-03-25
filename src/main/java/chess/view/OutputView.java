package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGuideMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Map<Position, Piece> board) {
        for (Row row : Row.reverseRows()) {
            printColumnWithRow(board, row);
            System.out.println();
        }
    }

    private static void printColumnWithRow(Map<Position, Piece> board, Row row) {
        for (Column column : Column.values()) {
            Position position = new Position(column, row);
            Piece piece = board.get(position);
            printStringOrDefault(piece);
        }
    }

    private static void printStringOrDefault(Piece piece) {
        if (piece != null) {
            System.out.print(piece.getName());
            return;
        }
        System.out.print(".");
    }

    public static void printMessage(String string) {
        System.out.println(string);
    }
}