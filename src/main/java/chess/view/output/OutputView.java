package chess.view.output;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
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
            System.out.print(getSymbolOfPosition(board, Position.of(file, rank)));
        }
    }

    private static String getSymbolOfPosition(final Board board, final Position position) {
        if (board.hasPieceInPosition(position)) {
            final Piece piece = board.findPieceInPosition(position);
            return PieceSymbol.findSymbol(piece);
        }
        return EMPTY_SYMBOL;
    }
}
