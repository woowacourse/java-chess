package view;

import java.util.List;
import model.Camp;
import model.GameBoard;
import model.Square;
import view.message.BoardInfoFormat;

public class OutputView {

    public void printGameBoard(GameBoard gameBoard) {
        for (List<Square> line : gameBoard.getBoard()) {
            for (Square square : line) {
                if (square.getSquareInfo().getCamp() == Camp.BLACK) {
                    System.out.print(BoardInfoFormat.from(square.getSquareInfo().getBoardInfo()).getValue().toUpperCase());
                }
                else {
                    System.out.print(BoardInfoFormat.from(square.getSquareInfo().getBoardInfo()).getValue());
                }
            }
            System.out.println();
        }
    }
}
