package chess.dto;

public final class UserDTO {
    private final int id;
    private final String nickname;

    public UserDTO(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }
}
