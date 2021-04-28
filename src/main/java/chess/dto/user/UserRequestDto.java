package chess.dto.user;

public class UserRequestDto {

    private final String name;

    public UserRequestDto(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
