package chess.dto;

import chess.domain.Team;

public class GameInformationDto {
    private int id;
    private Team turn;
    private boolean emptyCondition;

    private GameInformationDto(int id, Team turn) {
        this.id = id;
        this.turn = turn;
        this.emptyCondition = false;
    }

    private GameInformationDto(boolean emptyCondition) {
        this.emptyCondition = emptyCondition;
    }

    public static GameInformationDto of(int id, Team turn) {
        return new GameInformationDto(id, turn);
    }

    public static GameInformationDto empty() {
        return new GameInformationDto(true);
    }

    public int getId() {
        return id;
    }

    public Team getTurn() {
        return turn;
    }

    public boolean isEmpty() {
        return emptyCondition;
    }
}
