package domain.state;

import domain.ChessColumn;
import domain.ChessGame;
import domain.Rank;
import domain.Square;
import java.util.List;
import view.OutputView;

public class Move extends Running {

    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final int SQUARE_LENGTH = 2;


    public Move(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public State run(List<String> input) {
        Square source = getSourceSquare(input);
        Square destination = getDestinationSquare(input);
        chessGame.move(source, destination);
        OutputView.printChessBoard(chessGame);

        if (chessGame.isEnd()) {
            OutputView.printResult(chessGame.findWin());
            return new End();
        }
        return new Running(chessGame);
    }

    public Square getSourceSquare(List<String> input) {
        return toSquare(input.get(SOURCE_INDEX));
    }

    public Square getDestinationSquare(List<String> input) {
        return toSquare(input.get(DESTINATION_INDEX));
    }

    private Square toSquare(String squareInput) {
        validateSquareInput(squareInput);
        char column = squareInput.charAt(COLUMN_INDEX);
        char row = squareInput.charAt(ROW_INDEX);
        ChessColumn chessColumn = ChessColumn.find(column);
        Rank rank = Rank.find(Character.getNumericValue(row));
        return Square.of(chessColumn, rank);
    }

    private void validateSquareInput(String squareInput) {
        if (squareInput.length() != SQUARE_LENGTH) {
            throw new IllegalArgumentException("잘못된 위치 입력입니다.");
        }
    }
}
