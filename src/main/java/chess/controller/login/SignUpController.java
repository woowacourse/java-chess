package chess.controller.login;

import chess.controller.Controller;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.dao.UserDao;

public class SignUpController implements Controller {

    private final static SignUpController INSTANCE = new SignUpController();
    private static final int ID_INDEX = 1;
    private static final int PASSWORD_INDEX = 2;
    private static final int USER_NAME_INDEX = 3;

    private SignUpController() {
    }

    public static SignUpController getInstance() {
        return INSTANCE;
    }


    @Override
    public Response execute(Request request) {
        boolean result = UserDao.makeUserOf(getUserName(request), getId(request), getPassword(request));
        if (!result) {
            return new Response(ResponseType.FAIL, "해당 아이디 혹은 유저 네임이 중복 되었습니다.");
        }
        LoginSession.login(getId(request));
        return new Response(ResponseType.LOGIN);
    }

    private String getUserName(Request request) {
        String input = request.getInput();
        String[] split = input.split(" ");
        return split[USER_NAME_INDEX];
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
