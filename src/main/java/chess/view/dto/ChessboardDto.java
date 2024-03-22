package chess.view.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;
import chess.domain.chessboard.Chessboard;
import chess.domain.piece.Piece;

public class ChessboardDto {

    private final List<List<String>> chessboard;

    public ChessboardDto(final Chessboard chessboard) {
        Map<Position, Piece> board = chessboard.getChessboard();
        this.chessboard = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            this.chessboard.add(positionsOf(board, rank));
        }
    }

    private List<String> positionsOf(final Map<Position, Piece> board, final Rank rank) {
        List<String> positions = new ArrayList<>();
        for (final File file : File.values()) {
            Position position = Position.of(file, rank);
            Piece piece = board.get(position);
            positions.add(PieceTypeDto.of(piece));
        }
        return Collections.unmodifiableList(positions);
    }

    public List<List<String>> getChessboard() {
        return List.copyOf(chessboard);
    }
}
