package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {
    private static final int MAX_Y_POINT = 8;
    private static final int MIN_Y_POINT = 1;
    private static final int MIN_X_POINT = 1;
    private static final int MAX_X_POINT = 8;

    public static void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();

        for (int yPoint = MAX_Y_POINT; yPoint >= MIN_Y_POINT; yPoint--) {
            for (int xPoint = MIN_X_POINT; xPoint <= MAX_X_POINT; xPoint++) {
                if (xPoint == MAX_X_POINT) {
                    System.out.println(chessBoard.get(new Position(xPoint, yPoint)).getName());
                } else {
                    System.out.print(chessBoard.get(new Position(xPoint, yPoint)).getName());
                }
            }
        }
    }

    public static void printNoPieceMessage(String message) {
        System.out.println(message);
    }
}
