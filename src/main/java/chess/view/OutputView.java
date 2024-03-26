package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public void printChessBoard(final Map<Position, Piece> pieces) {
        final List<List<Character>> board = sortByBoardOrder(pieces);
        board.forEach(this::printChessRow);
    }

    private void printChessRow(final List<Character> row) {
        row.forEach(System.out::print);
        System.out.println();
    }

    private List<List<Character>> sortByBoardOrder(final Map<Position, Piece> pieces) {
        final List<List<Character>> board = new ArrayList<>();
        for (int i = 0; i < Rank.values().length; i++) {
            board.add(new ArrayList<>(List.of('.', '.', '.', '.', '.', '.', '.', '.')));
        }

        for (Entry<Position, Piece> entry : pieces.entrySet()) {
            final int fileIndex = entry.getKey().getFileIndex() - 1;
            final int rankIndex = 7 - (entry.getKey().getRankIndex() - 1);

            final List<Character> marks = board.get(rankIndex);
            marks.set(fileIndex, ChessBoardMarker.getSymbol(entry.getValue()));
        }

        return board;
    }
}
