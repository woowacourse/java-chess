package chess.controller.user;

import chess.controller.main.Request;
import java.util.List;

public class LoginRequest {

    private static final int USER_NAME_INDEX = 1;
    private static final int LOGIN_SIZE = 2;

    private final String userName;

    private LoginRequest(String userName) {
        this.userName = userName;
    }

    public static LoginRequest from(Request request) {
        List<String> commands = request.commands();
        if (commands.size() != LOGIN_SIZE) {
            throw new IllegalArgumentException("로그인 요청은 login [유저 이름] 형식으로 작성되어야 합니다");
        }
        String userName = commands.get(USER_NAME_INDEX);
        return new LoginRequest(userName);
    }

    public String getUserName() {
        return userName;
    }
}
