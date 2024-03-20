package view;

import java.util.Map;
import java.util.Map.Entry;
import model.GameBoard;
import model.piece.Piece;
import model.position.Position;

public class OutputView {

    public void printStartMessage() {
        //TODO 상수화
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printGameBoard(GameBoard gameBoard) {
        Map<Position, Piece> board = gameBoard.getBoard();
        String[][] res = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                res[i][j] = ".";
            }
        }

        for (Entry<Position, Piece> entry : board.entrySet()) {
            res[entry.getKey().getRowIndex()][entry.getKey().getColumnIndex()] = entry.getValue()
                    .toString();
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(res[i][j]);
            }
            System.out.print("   " + (8 - i));
            System.out.println();
        }

        System.out.printf("%nabcdefgh%n");
    }


    public void printException(final Exception exception) {
        System.out.printf("[ERROR] %s%n", exception.getMessage());
    }
}
