package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatusDto {

    private final Map<Color, Double> statuses = new HashMap<>();

    private StatusDto(final Status status) {
        for (Color color : Color.values()) {
            statuses.put(color, status.calculateScore(color));
        }
    }

    public static StatusDto from(final Map<Position, Piece> board) {
        Status status = new Status(new Board(() -> board));
        return new StatusDto(status);
    }

    public Map<Color, Double> getStatus() {
        return Collections.unmodifiableMap(statuses);
    }
}
