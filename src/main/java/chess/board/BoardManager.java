package chess.board;

import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import chess.view.InputView;
import chess.view.OutputView;

public class BoardManager {

    public void move() {
        Board board = BoardFactory.init();
        while(true)
        {
            OutputView.printBoard(board);
            String inputCurrentCoordinate = InputView.inputCurrentCoordinate();
            if (inputCurrentCoordinate.equals("Q")) {
                break;
            }
            String inputDestinationCoordinate = InputView.inputDestinationCoordinate();
            Position currentPosition = parsePosition(inputCurrentCoordinate);
            Position destinationPosition = parsePosition(inputDestinationCoordinate);
            board.move(currentPosition, destinationPosition);
        }
    }

    private Position parsePosition(String input) {
        String[] coordinate = input.split("");
        Column column = Column.valueOf(coordinate[0]);
        int rowNumber = Integer.parseInt(coordinate[1]);
        Row row = Row.values()[8 - rowNumber];
        return new Position(column, row);
    }
}
