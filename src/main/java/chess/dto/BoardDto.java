package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public final class BoardDto {

    private final Map<String, PieceDto> coordinates;

    public BoardDto(final Board board) {
        this.coordinates = boardToString(board);
    }

    private Map<String, PieceDto> boardToString(final Board board) {
        final Map<String, PieceDto> stringCoordinates = new HashMap<>();
        final Map<Position, Piece> coordinates = board.coordinates();
        for (Position position : coordinates.keySet()) {
            final Piece piece = board.pieceAt(position);
            stringCoordinates.put(position.name(), new PieceDto(piece));
        }
        return stringCoordinates;
    }

    public Map<String, PieceDto> coordinates() {
        return coordinates;
    }
}
