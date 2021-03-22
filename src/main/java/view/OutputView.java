package view;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class OutputView {

    public static void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = chessBoard.get(new Position(i, j));
                System.out.print(piece.getName());
            }
            System.out.println();
        }
    }

    public static void printScore(Board board){
        System.out.println("검은색 : " + board.piecesScore(true).value() + "점");
        System.out.println("흰색 : " + board.piecesScore(false).value() + "점");
    }

}
