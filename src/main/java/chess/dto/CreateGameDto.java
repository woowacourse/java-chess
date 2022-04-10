package chess.dto;

import java.util.Objects;

public class CreateGameDto {

    private final int id;

    public CreateGameDto(int id) {
        this.id = id;
    }

    public String toJson() {
        return "{\"gameId\":" + id + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateGameDto that = (CreateGameDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CreateGameDto{" + "id=" + id + '}';
    }
}
