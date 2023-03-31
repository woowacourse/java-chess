package chess.repository.user;

public class UserDto {

    private final int userId;

    public UserDto(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
