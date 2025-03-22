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
        Board board = new Board();
        String input = "start";
        while (!input.equals("end")) {
            boardView.display(board);
            input = scanner.nextLine();
            char srcColumn = input.charAt(5);
            char srcRow = input.charAt(6);
            char desColumn = input.charAt(8);
            char desRow = input.charAt(9);
            Position start = new Position(rowInput.get(srcRow), columnInput.get(srcColumn));
            Position end = new Position(rowInput.get(desRow), columnInput.get(desColumn));
            board.move(start, end);
        }
    }
}
