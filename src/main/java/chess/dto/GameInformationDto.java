package chess.dto;

import chess.domain.Team;

public class GameInformationDto {
    private int id;
    private Team turn;

    private GameInformationDto(int id, Team turn) {
        this.id = id;
        this.turn = turn;
    }

    public static GameInformationDto of(int id, Team turn) {
        return new GameInformationDto(id, turn);
    }

    public int getId() {
        return id;
    }

    public Team getTurn() {
        return turn;
    }
}
