package chess;

import chess.board.Board;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import chess.view.BoardView;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private final BoardView boardView = new BoardView();
    private final Scanner scanner = new Scanner(System.in);
    private final Map<Character, Column> columnInput = Map.of(
            'A', Column.A,
            'B', Column.B,
            'C', Column.C,
            'D', Column.D,
            'F', Column.F,
            'G', Column.G,
            'H', Column.H);
    private final Map<Character, Row> rowInput = Map.of(
            '1', Row.ONE,
            '2', Row.TWO,
            '3', Row.THREE,
            '4', Row.FOUR,
            '5', Row.FIVE,
            '6', Row.SIX,
            '7', Row.SEVEN);

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {

        System.out.println("\n"
                + "RNBQKBNR 8 (rank 8)\n"
                + "PPPPPPPP 7 (rank 7)\n"
                + "........ 6\n"
                + "........ 5\n"
                + "........ 4\n"
                + "........ 3\n"
                + "pppppppp 2\n"
                + "rnbqkbnr 1 (rank 1)\n"
                + "abcdefgh\n"
                + "\n"
                + "- 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.\n"
                + "- 각 진영이 번갈아가면서 기물을 움직여야 하며 흰색(소문자)가 먼저 움직인다.\n"
                + "- 게임 종료: end\n"
                + "- 게임 이동: move source target - 예) move B2 B3");

        Board board = new Board();
        String input = "start";
        while (true) {
            boardView.display(board);
            input = scanner.nextLine();
            if (input.equals("end")) {
                return;
            }
            char srcColumn = input.charAt(5);
            char srcRow = input.charAt(6);
            char desColumn = input.charAt(8);
            char desRow = input.charAt(9);
            try {
                Position start = new Position(rowInput.get(srcRow), columnInput.get(srcColumn));
                Position end = new Position(rowInput.get(desRow), columnInput.get(desColumn));
                board.move(start, end);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
