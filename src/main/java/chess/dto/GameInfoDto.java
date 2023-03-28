package chess.dto;

import chess.domain.Color;
import chess.domain.GameStatus;

public class GameInfoDto {
    private final GameStatus status;
    private final Color turn;

    private GameInfoDto(GameStatus status, Color turn) {
        this.status = status;
        this.turn = turn;
    }

    public static GameInfoDto create(String status, String turn) {
        return new GameInfoDto(GameStatus.valueOf(status), Color.valueOf(turn));
    }

    public static GameInfoDto create(GameStatus status, Color turn) {
        return new GameInfoDto(status, turn);
    }

    public GameStatus getStatus() {
        return status;
    }

    public Color getTurn() {
        return turn;
    }
}
