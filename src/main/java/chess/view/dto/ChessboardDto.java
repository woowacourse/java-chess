package chess.view.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.chessboard.Chessboard;
import chess.domain.piece.Piece;

public class ChessboardDto {

    private final List<List<String>> chessboard;

    public ChessboardDto(final Chessboard chessboard) {
        Map<Square, Piece> board = chessboard.getChessboard();
        this.chessboard = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            this.chessboard.add(squaresOf(board, rank));
        }
    }

    private List<String> squaresOf(final Map<Square, Piece> board, final Rank rank) {
        List<String> squares = new ArrayList<>();
        for (final File file : File.values()) {
            Square square = Square.of(file, rank);
            Piece piece = board.get(square);
            squares.add(PieceTypeDto.of(piece));
        }
        return Collections.unmodifiableList(squares);
    }

    public List<List<String>> getChessboard() {
        return List.copyOf(chessboard);
    }
}
