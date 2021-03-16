package chess.view;

import chess.domain.Board;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class OutputView {

    public static void startGame() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Position position = new Position(row, column);
                System.out.print(board.getInitial(position));
            }
            System.out.println();
        }

    }
}
