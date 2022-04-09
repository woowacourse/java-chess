package chess.domain.dto;

import chess.domain.game.Color;
import chess.domain.game.Status;

public class GameDto {
    private final int id;
    private final boolean status;
    private final String turn;

    public GameDto(int id, boolean status, String turn) {
        this.id = id;
        this.status = status;
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        if (status) {
            return Status.PLAYING;
        }
        return Status.END;
    }

    public Color getTurn() {
        return Color.from(turn);
    }
}
