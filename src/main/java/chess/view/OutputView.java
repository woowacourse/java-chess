package chess.view;

import chess.domain.Board;
import chess.domain.Piece;

import java.util.List;

public class OutputView {
    public static void printChessBoard(Board board) {
        List<List<Piece>> boards = board.getBoard();
        for (List<Piece> rank : boards) {
            for (Piece piece : rank) {
                System.out.print(PieceSymbolMapper.getSymbol(piece));
            }
            System.out.println();
        }
    }
}
