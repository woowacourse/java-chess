package chess.dto;

public class UserDto {
    private final int id;
    private final String name;

    public UserDto(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
