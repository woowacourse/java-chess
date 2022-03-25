package chess.view.output;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String EMPTY_SYMBOL = ".";

    private OutputView() {
        throw new AssertionError();
    }

    public static void printCurrentBoard(final Board board) {
        final List<Rank> reverseOrderRanks = getReverseOrderRanks();
        for (Rank rank : reverseOrderRanks) {
            printCurrentBoard(board, rank);
            System.out.println();
        }
    }

    private static List<Rank> getReverseOrderRanks() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
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
