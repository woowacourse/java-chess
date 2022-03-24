package chess.view;

import chess.domain.Grid;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public class OutputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" +
            "> 게임 종료 : end\n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printBoard(Grid[][] board) {
        for (int rank = 0; rank < 8; rank++) {
            for (int column = 0; column < 8; column++) {
                System.out.print(board[rank][column].getPiece().signature());
            }
            System.out.println("\t(rank" + PositionY.of(rank).getName() + ")");
        }
        System.out.println();
        for (PositionX positionX : PositionX.values()) {
            System.out.print(positionX.getName());
        }
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
