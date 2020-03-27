package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {

    public static void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();

        for (int yPoint = 8; 1 <= yPoint; yPoint--) {
            for (int xPoint = 1; xPoint <= 8; xPoint++) {
                if (xPoint == 8) {
                    System.out.println(chessBoard.get(new Position(xPoint, yPoint)).getName());
                } else {
                    System.out.print(chessBoard.get(new Position(xPoint, yPoint)).getName());
                }
            }
        }

    }
}
