package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
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

        for (Entry<Position, Piece> positionPiece: pieces.entrySet()) {
            final Position position = positionPiece.getKey();
            final int fileIndex = position.getFile().getIndex() - 1;
            final int rankIndex = 7 - (position.getRank().getIndex() - 1);
            final List<String> marks = board.get(rankIndex);
            marks.set(fileIndex, convertToMark(positionPiece.getValue()));
        }

        return board;
    }

    private String convertToMark(final Piece piece) {
        if (piece.isBlack()) {
            return String.valueOf(Character.toUpperCase(piece.getClass().getSimpleName().charAt(0)));
        }
        if (piece.isWhite()){
            return String.valueOf(Character.toLowerCase(piece.getClass().getSimpleName().charAt(0)));
        }
        return ".";
    }
}
