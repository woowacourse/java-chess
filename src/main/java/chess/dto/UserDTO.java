package chess.dto;

public class UserDTO {
    private int id;
    private String nickname;

    public UserDTO(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }
}
