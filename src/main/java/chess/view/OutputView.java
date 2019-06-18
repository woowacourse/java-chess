package chess.view;

import chess.domain.Board;
import chess.domain.Coordinate;
import chess.domain.Position;

public class OutputView {
    public static void start() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void command() {
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void board() {
        for (int i = 8; i > 0; i--) {
            for(int j = 1; j <= 8; j++) {
                System.out.print(Board.boardAt(new Position(new Coordinate(j), new Coordinate(i))));
            }
            System.out.println();
        }
    }
}
