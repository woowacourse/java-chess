package dto;

import chess.domain.Aliance;

public class GameDTO {
    private final int id;
    private final boolean isEnd;
    private final Aliance turn;

    public GameDTO(int id, boolean isEnd, int teamId) {
        this.id = id;
        this.isEnd = isEnd;
        this.turn = Aliance.valueOf(teamId);
    }

    public int getId() {
        return id;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Aliance getTurn() {
        return turn;
    }
}
