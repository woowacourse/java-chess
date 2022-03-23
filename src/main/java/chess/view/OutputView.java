package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import chess.domain.Rank;

import java.util.List;

public class OutputView {
    public static void printChessBoard(Board board) {
        List<Rank> boards = board.getBoard();
        for (Rank rank : boards) {
            printRank(rank);
        }
    }

    private static void printRank(Rank rank) {
        for (Piece piece : rank.getPieces()) {
            System.out.print(PieceSymbolMapper.getSymbol(piece));
        }
        System.out.println();
    }
}
