package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {
    private static final char MIN_Y_POINT = '1';
    private static final char MAX_Y_POINT = '8';
    private static final char MIN_X_POINT = 'a';
    private static final char MAX_X_POINT = 'h';

    public static void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.get();

        for (char yPoint = MAX_Y_POINT; yPoint >= MIN_Y_POINT; yPoint--) {
            for (char xPoint = MIN_X_POINT; xPoint <= MAX_X_POINT; xPoint++) {
                if (xPoint == MAX_X_POINT) {
                    System.out.println(chessBoard.get(Positions.of(xPoint, yPoint)).getName());
                } else {
                    System.out.print(chessBoard.get(Positions.of(xPoint, yPoint)).getName());
                }
            }
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
