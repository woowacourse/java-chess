package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Piece;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Piece piece = board.get(new Position(column, row));
                System.out.print(PieceOutputText.of(piece));
            }
            System.out.println();
        }
        System.out.println();
    }
}
