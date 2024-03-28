package chess.view.dto;

import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.chessboard.Chessboard;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessboardDto {

    private final List<List<String>> chessboard;

    public ChessboardDto(final Chessboard chessboard) {
        Map<Square, Piece> board = chessboard.getChessboard();
        this.chessboard = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            List<String> squares = new ArrayList<>();
            this.chessboard.add(squares);
            addSquares(board, rank, squares);
        }
    }

    private void addSquares(final Map<Square, Piece> board, final Rank rank, final List<String> squares) {
        for (final File file : File.values()) {
            Square square = Square.of(file, rank);
            Piece piece = board.get(square);
            squares.add(PieceTypeDto.of(piece));
        }
    }

    public List<List<String>> getChessboard() {
        return chessboard;
    }
}
