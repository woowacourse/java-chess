package chess.controller.login;

import chess.controller.Controller;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.dao.UserDao;

public class LoginController implements Controller {
    private final static LoginController INSTANCE = new LoginController();
    private static final int ID_INDEX = 1;
    private static final int PASSWORD_INDEX = 2;

    private LoginController() {
    }

    public static LoginController getInstance() {
        return INSTANCE;
    }


    @Override
    public Response execute(Request request) {
        String userName = UserDao.getUserNameOf(getId(request), getPassword(request));
        if (userName == null) {
            return new Response(ResponseType.FAIL, "해당 아이디 비밀번호의 유저는 존재하지 않습니다.");
        }
        LoginSession.login(getId(request));
        return new Response(ResponseType.LOGIN);
    }

    private String getId(Request request) {
        String input = request.getInput();
        String[] split = input.split(" ");
        return split[ID_INDEX];
    }

    private String getPassword(Request request) {
        String input = request.getInput();
        String[] split = input.split(" ");
        return split[PASSWORD_INDEX];
    }
}
