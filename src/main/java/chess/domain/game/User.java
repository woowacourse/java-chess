package chess.domain.game;

public class User {

    private final String id;
    private final String nickname;

    public User(String id, String nickname) {
        validate(id, nickname);
        this.id = id;
        this.nickname = nickname;
    }

    private void validate(String id, String nickname) {
        if (id.length() > 20) {
            throw new IllegalArgumentException("아이디는 20자를 초과할 수 없습니다.");
        }
        if (nickname.length() > 20) {
            throw new IllegalArgumentException("닉네임은 20자를 초과할 수 없습니다.");
        }
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
