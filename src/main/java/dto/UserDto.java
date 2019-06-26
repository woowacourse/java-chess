package dto;

import chess.domain.Aliance;

import java.util.Objects;

public class UserDto {
    private final int gameId;
    private final String name;
    private final Aliance aliance;


    public UserDto(final int gameId, String name, int teamId) {
        this.gameId = gameId;
        this.name = name;
        this.aliance = Aliance.valueOf(teamId);
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public Aliance getAliance() {
        return aliance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return gameId == userDto.gameId &&
                Objects.equals(name, userDto.name) &&
                aliance == userDto.aliance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, name, aliance);
    }
}
