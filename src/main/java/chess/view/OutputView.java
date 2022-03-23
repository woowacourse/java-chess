package chess.view;

import chess.domain.Grid;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public class OutputView {
    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printBoard(Grid[][] board) {
        for (int rank = 0; rank < 8; rank++) {
            for (int column = 0; column < 8; column++) {
                System.out.print(board[rank][column].getPiece().signature());
            }
            System.out.println("\t(" + PositionY.find(rank).getName() + ")");
        }
        System.out.println();
        for (PositionX positionX : PositionX.values()) {
            System.out.print(positionX.getName());
        }
    }
}
