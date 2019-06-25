package chess.model;

import java.util.Arrays;

public class ChessGame {
    private Board board;
    private int turn;

    public ChessGame() {
        turn = 0;
        board = new Board(new NormalCreateStrategy());
    }

    public boolean isRightTurn(String sourceCoordinate) {
        return board.isRightTurn(sourceCoordinate, turn);
    }

    public void movePiece(String sourceCoordinate, String targetCoordinate) {
        board.movePiece(Arrays.asList(sourceCoordinate, targetCoordinate));
        turn++;
    }
}
