package chess.view;

import chess.domain.Score;
import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.Locale;

public class OutputView {

    public static void startGame() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void print(Board board, Side side) {
        print(board);
        print(side);
    }

    public static void print(Board board) {
        System.out.println();

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Position position = new Position(column, row);
                System.out.print(board.getInitial(position));
            }
            System.out.println("  " + row.getLineName());
        }
        System.out.println();
        Arrays.stream(Column.values())
                .map(column -> column.name().toLowerCase(Locale.ROOT))
                .forEach(System.out::print);

        System.out.println();
    }

    private static void print(Side side) {
        System.out.println("Current Turn: " + side.name());
    }

    public static void print(Score score) {
        System.out.println("Black: " + score.blackScore());
        System.out.println("White: " + score.whiteScore());
    }

    public static void printWinner(Side winner) {
        System.out.println();
        System.out.println("Winner: " + winner.name());
    }

    public static void printError(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
