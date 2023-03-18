package chess.board.dto;

import chess.board.Board;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {
    private final List<List<String>> nameBoard;

    private BoardDto(final List<List<String>> nameBoard) {
        this.nameBoard = new ArrayList<>(nameBoard);
    }

    public static BoardDto of(final Board board) {
        List<List<String>> nameBoard = new ArrayList<>();
        initBoardSpaces(nameBoard);
        fillPieceNames(board, nameBoard);
        return new BoardDto(nameBoard);
    }

    private static void initBoardSpaces(List<List<String>> nameBoard) {
        final List<String> emptyNames = List.of(".", ".", ".", ".", ".", ".", ".", ".");
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            List<String> row = new ArrayList<>(emptyNames);
            nameBoard.add(row);
        }
    }

    private static void fillPieceNames(final Board board, final List<List<String>> nameBoard) {
        List<Piece> pieces = board.getPieces();
        for (Piece piece : pieces) {
            final NameDto nameDto = new NameDto(piece);
            final String pieceName = nameDto.getName();
            final List<String> rank = nameBoard.get(getFlippedIndex(piece.getRank()));
            final int fileIndex = piece.getFile() - 1;
            rank.set(fileIndex, pieceName);
        }
    }

    private static int getFlippedIndex(int originalIndex) {
        return Math.abs(originalIndex - 8);
    }

    public List<List<String>> getNameBoard() {
        return List.copyOf(nameBoard);
    }
}
