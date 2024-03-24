package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.type.Knight;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public void printChessBoard(final Map<Position, Piece> pieces) {
        final List<List<String>> board = sortByBoardOrder(pieces);
        board.forEach(this::printChessRow);
    }

    private void printChessRow(final List<String> row) {
        row.forEach(System.out::print);
        System.out.println();
    }

    private List<List<String>> sortByBoardOrder(final Map<Position, Piece> pieces) {
        final List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < Rank.values().length; i++) {
            board.add(new ArrayList<>(List.of(".", ".", ".", ".", ".", ".", ".", ".")));
        }

        for (Entry<Position, Piece> entry : pieces.entrySet()) {
            final int fileIndex = entry.getKey().getFile().getIndex() - 1;
            final int rankIndex = 7 - (entry.getKey().getRank().getIndex() - 1);
            final List<String> marks = board.get(rankIndex);
            marks.set(fileIndex, convertToMark(entry.getValue()));
        }

        return board;
    }

    private String convertToMark(final Piece piece) {
        if (piece.isBlack()) {
            if (piece instanceof Knight) {
                return "N";
            }
            return String.valueOf(Character.toUpperCase(piece.getClass().getSimpleName().charAt(0)));
        }

        if (piece instanceof Knight) {
            return "n";
        }

        return String.valueOf(Character.toLowerCase(piece.getClass().getSimpleName().charAt(0)));
    }
}
