package chess.domain.dto;

import chess.domain.game.Color;
import chess.domain.game.GameStatus;

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

    public GameStatus getStatus() {
        if (status) {
            return GameStatus.PLAYING;
        }
        return GameStatus.END;
    }

    public Color getTurn() {
        return Color.from(turn);
    }
}
