package chess.view.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;
import chess.domain.piece.Piece;

public class ChessboardDto {

    private final List<List<String>> chessboard;

    public ChessboardDto(final Map<Position, Piece> chessboard) {
        this.chessboard = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            List<String> squares = new ArrayList<>();
            this.chessboard.add(squares);
            addSquares(chessboard, rank, squares);
        }
    }

    private void addSquares(final Map<Position, Piece> chessboard, final Rank rank, final List<String> squares) {
        for (final File file : File.values()) {
            Position position = Position.of(file, rank);
            Piece piece = chessboard.get(position);
            squares.add(PieceTypeDto.of(piece));
        }
    }

    public List<List<String>> getChessboard() {
        return chessboard;
    }
}
