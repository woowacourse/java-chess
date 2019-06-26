package dto;

import chess.domain.Aliance;

public class UserDto {
    private final String name;
    private final Aliance aliance;

    public UserDto(String name, int teamId) {
        this.name = name;
        this.aliance = Aliance.valueOf(teamId);
    }

    public String getName() {
        return name;
    }

    public Aliance getAliance() {
        return aliance;
    }

}
