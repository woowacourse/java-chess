package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OutputView {

    public void printChessBoard(final Set<Piece> pieces) {
        final List<List<String>> board = sortByBoardOrder(pieces);
        board.forEach(this::printChessRow);
    }

    private void printChessRow(final List<String> row) {
        row.forEach(System.out::print);
        System.out.println();
    }

    private List<List<String>> sortByBoardOrder(final Set<Piece> pieces) {
        final List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<>(List.of(".", ".", ".", ".", ".", ".", ".", ".")));
        }

        for (Piece piece : pieces) {
            final Position position = piece.getPosition();
            final int fileIndex = position.getFile().getIndex() - 1;
            final int rankIndex = 7 - (position.getRank().getNumber() - 1);
            final List<String> marks = board.get(rankIndex);
            marks.set(fileIndex, convertToMark(piece));
        }

        return board;
    }

    private String convertToMark(final Piece piece) {
        if (piece.isBlack()) {
            return String.valueOf(Character.toUpperCase(piece.getClass().getSimpleName().charAt(0)));
        }
        return String.valueOf(Character.toLowerCase(piece.getClass().getSimpleName().charAt(0)));
    }
}
