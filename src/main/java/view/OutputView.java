package view;

import java.util.List;
import model.GameBoard;
import piece.Piece;

public class OutputView {

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printGameBoard(GameBoard gameBoard) {
        for (List<Piece> line : gameBoard.getBoard()) {
            for (Piece piece : line) {
                System.out.print(piece);
            }
            System.out.println();
        }
        System.out.println();
    }


    public void printException(final Exception exception) {
        System.out.printf("[ERROR] %s%n", exception.getMessage());
    }
}
