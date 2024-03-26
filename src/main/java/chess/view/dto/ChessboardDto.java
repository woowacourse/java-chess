package chess.view.dto;

import java.util.List;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Square;

public class ChessboardDto {

    private final List<List<String>> chessboard;

    public ChessboardDto(final Chessboard chessboard) {
        this.chessboard = initialize(chessboard.getSquares());
    }

    private List<List<String>> initialize(final List<List<Square>> squares) {
        return squares.stream()
                .map(this::initializeRow)
                .toList();
    }

    private List<String> initializeRow(final List<Square> row) {
        return row.stream()
                .map(SquareCharacter::from)
                .toList();
    }

    public List<List<String>> get() {
        return List.copyOf(chessboard);
    }
}
