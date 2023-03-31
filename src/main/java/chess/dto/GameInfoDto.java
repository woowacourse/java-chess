package chess.dto;

import chess.domain.Color;
import chess.domain.GameStatus;

public class GameInfoDto {
    private final int id;
    private final GameStatus status;
    private final Color turn;

    public GameInfoDto(int id, GameStatus status, Color turn) {
        this.id = id;
        this.status = status;
        this.turn = turn;
    }

    public static GameInfoDto create(int id, String status, String turn) {
        return new GameInfoDto(id, GameStatus.valueOf(status), Color.valueOf(turn));
    }

    public static GameInfoDto create(int id, GameStatus status, Color turn) {
        return new GameInfoDto(id, status, turn);
    }

    public int getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Color getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return "GameInfoDto{" +
                "id=" + id +
                ", status=" + status +
                ", turn=" + turn +
                '}';
    }
}
