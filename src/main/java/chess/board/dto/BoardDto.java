package chess.board.dto;

import chess.board.Board;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {
    private static final int BOARD_ROW_COLUMN_SIZE = 8;

    private final List<List<String>> nameBoard;

    public BoardDto(final Board board) {
        this.nameBoard = new ArrayList<>();
        initBoardSpaces();
        fillPieceNames(board);
    }

    private void initBoardSpaces() {
        final List<String> emptyNames = List.of(".", ".", ".", ".", ".", ".", ".", ".");
        for (int rowIndex = 0; rowIndex < BOARD_ROW_COLUMN_SIZE; rowIndex++) {
            List<String> row = new ArrayList<>(emptyNames);
            nameBoard.add(row);
        }
    }

    private void fillPieceNames(final Board board) {
        List<Piece> pieces = board.getPieces();
        for (Piece piece : pieces) {
            final NameDto nameDto = new NameDto(piece);
            final String pieceName = nameDto.getName();
            final List<String> rank = nameBoard.get(getFlippedIndex(piece.getRank()));
            final int fileIndex = piece.getFile() - 1;
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
