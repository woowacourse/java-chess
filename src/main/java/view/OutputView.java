package view;

import java.util.List;
import model.Camp;
import model.GameBoard;
import model.Square;
import view.message.BoardInfoFormat;

public class OutputView {

    public void printStartMessage() {
        System.out.printf("체스 게임을 시작합니다.%n게임 시작은 %s, 종료는 %s 명령을 입력하세요.%n", "start", "end");
    }

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
