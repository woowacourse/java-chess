package view;

import java.util.List;
import model.GameBoard;
import model.Square;

public class OutputView {

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printGameBoard(GameBoard gameBoard) {
        for (List<Square> line : gameBoard.getBoard2()) {
            for (Square square : line) {
                System.out.print(square.getPiece());
            }
            System.out.println();
        }
        System.out.println();
    }


    public void printException(final Exception exception) {
        System.out.printf("[ERROR] %s%n", exception.getMessage());
    }
}
