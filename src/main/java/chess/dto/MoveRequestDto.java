package chess.dto;

import chess.domain.position.Position;
import com.google.gson.Gson;

public class MoveRequestDto {

    private final String from;
    private final String to;

    private MoveRequestDto(final String from, final String to) {
        this.from = from;
        this.to = to;
    }

    public static MoveRequestDto from(final String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, MoveRequestDto.class);
    }

    public Position getFrom() {
        return Position.from(from);
    }

    public Position getTo() {
        return Position.from(to);
    }
}
