package chess.controller.login;

import chess.controller.Controller;
import chess.controller.GameCommand;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LoginSessionController {
    private final static LoginSessionController INSTANCE = new LoginSessionController();
    private final Map<GameCommand, Controller> controllers;
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    private LoginSessionController() {
        controllers = makeControllers();
    }

    public static LoginSessionController getInstance() {
        return INSTANCE;
    }

    private Map<GameCommand, Controller> makeControllers() {
        Map<GameCommand, Controller> controllers = new HashMap<>();
        controllers.put(GameCommand.LOGIN, LoginController.getInstance());
        controllers.put(GameCommand.SIGNUP, SignUpController.getInstance());
        return controllers;
    }

    public void ensureLogin() {
        outputView.printLoginMessage();
        Request request = readLoginRequest();
        GameCommand gameCommand = request.getGameCommand();
        Controller controller = controllers.get(gameCommand);
        Response response = controller.execute(request);
        if (response.getType() == ResponseType.FAIL) {
            outputView.printFailReason(response.getCause());
            ensureLogin();
        }
    }

    private Request readLoginRequest() {
        Request request = repeat(() -> new Request(inputView.inputGameCommand()));
        GameCommand gameCommand = request.getGameCommand();
        if (gameCommand == GameCommand.LOGIN || gameCommand == GameCommand.SIGNUP) {
            return request;
        }
        outputView.ensureLoginMessage();
        return readLoginRequest();
    }

    private <T> T repeat(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printError(e);
            }
        }
    }
}
