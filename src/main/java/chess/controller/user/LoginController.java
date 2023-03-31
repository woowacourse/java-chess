package chess.controller.user;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.user.LoginService;

public class LoginController implements Controller {

    private final Login login;
    private final LoginOutput loginOutput;
    private final LoginService loginService;

    public LoginController(Login login, LoginOutput loginOutput, LoginService loginService) {
        this.login = login;
        this.loginOutput = loginOutput;
        this.loginService = loginService;
    }

    @Override
    public void run(Request request) {
        LoginRequest loginRequest = LoginRequest.from(request);
        int userId = loginService.login(loginRequest.getUserName());
        login.login(userId);
        loginOutput.printLoginSuccess();
    }
}
