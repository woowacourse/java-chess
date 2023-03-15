package chess.board.dto;

import chess.board.Board;
import chess.piece.Piece;
import chess.piece.dto.NameDto;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {
    private final List<List<String>> nameBoard;

    public BoardDto(final Board board) {
        this.nameBoard = new ArrayList<>();
        initBoardSpaces();
        fillPieceNames(board);
    }

    private void fillPieceNames(final Board board) {
        List<Piece> pieces = board.getPieces();
        for (Piece piece : pieces) {
            final NameDto nameDto = new NameDto(piece);
            final String pieceName = nameDto.getName();
            final List<String> names = nameBoard.get(piece.getRank());
            names.set(piece.getFile(), pieceName);
        }
    }

    private void initBoardSpaces() {
        final List<String> emptyNames = List.of(".", ".", ".", ".", ".", ".", ".", ".");
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            List<String> row = new ArrayList<>(emptyNames);
            nameBoard.add(row);
        }
    }

    public BoardDto(final List<List<String>> nameBoard) {
        this.nameBoard = List.copyOf(nameBoard);
    }
}
