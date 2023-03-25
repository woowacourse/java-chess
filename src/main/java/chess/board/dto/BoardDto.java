package chess.board.dto;

import chess.board.Board;
import chess.piece.type.Piece;
import chess.view.PieceName;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private final List<List<String>> nameBoard;

    public BoardDto(final Board board) {
        this.nameBoard = new ArrayList<>();
        initBoardSpaces(board.getSideLength());
        fillPieceNames(board);
    }

    private void initBoardSpaces(int length) {
        final List<String> emptyNames = List.of(".", ".", ".", ".", ".", ".", ".", ".");
        for (int rowIndex = 0; rowIndex < length; rowIndex++) {
            List<String> row = new ArrayList<>(emptyNames);
            nameBoard.add(row);
        }
    }

    private void fillPieceNames(final Board board) {
        List<Piece> pieces = board.getPieces();
        for (Piece piece : pieces) {
            final List<String> rank = nameBoard.get(getFlippedIndex(piece.getRank()));
            final int fileIndex = piece.getFile() - 1;
            final String pieceName = PieceName.of(piece.getClass(), piece.getSide());
            rank.set(fileIndex, pieceName);
        }
    }

    private int getFlippedIndex(int originalIndex) {
        return Math.abs(originalIndex - nameBoard.size());
    }

    public List<List<String>> getNameBoard() {
        return List.copyOf(nameBoard);
    }
}
