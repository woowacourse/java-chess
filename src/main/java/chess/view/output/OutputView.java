package chess.view.output;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;

public class OutputView {

    private static final String EMPTY_SYMBOL = ".";

    private OutputView() {
        throw new AssertionError();
    }

    public static void printCurrentBoard(final Board board) {
        for (Rank rank : Rank.values()) {
            printCurrentBoard(board, rank);
            System.out.println();
        }
    }

    private static void printCurrentBoard(final Board board, final Rank rank) {
        for (File file : File.values()) {
            System.out.print(getSymbolOfPosition(board, file, rank));
        }
    }

    private static String getSymbolOfPosition(final Board board, final File file, final Rank rank) {
        if (board.hasPieceInPosition(file, rank)) {
            final Piece piece = board.findPieceInPosition(file, rank);
            return PieceSymbol.findSymbol(piece);
        }
        return EMPTY_SYMBOL;
    }
}
