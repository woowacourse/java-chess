package chess;

import chess.view.InputView;
import chess.view.ResultView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Board board = new Board();
        resultView.printBoard(board);

        while (true) {
            String movement = inputView.readMovement();
            if (movement.equals("exit")) {
                break;
            }
            String[] tokens = movement.split(" ");
            String[] tokens1 = tokens[0].split(",");
            String[] tokens2 = tokens[1].split(",");
            Position start = new Position(Row.of(tokens1[1]), Column.valueOf(tokens1[0]));
            Position destination = new Position(Row.of(tokens2[1]), Column.valueOf(tokens2[0]));
            System.out.println("start = " + start);
            System.out.println("destination = " + destination);
            board.move(start, destination);

            resultView.printBoard(board);
        }
    }

}
