package chess.view;

import chess.domain.Piece;
import chess.domain.Rank;
import chess.domain.Row;

import java.util.Map;

public class OutputView {
    public static void printChessBoard(Map<Row, Rank> board) {
        for (Rank rank : board.values()) {
            printRank(rank);
        }
    }

    private static void printRank(Rank rank) {
        for (Piece piece : rank.getPieces().values()) {
            System.out.print(PieceSymbolMapper.getSymbol(piece));
        }
        System.out.println();
    }
}
