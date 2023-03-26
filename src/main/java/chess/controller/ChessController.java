package chess.controller;

import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    private final Map<GameCommand, Controller> controllers;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        controllers = makeControllers();

    }

    private Map<GameCommand, Controller> makeControllers() {
        Map<GameCommand, Controller> controllers = new HashMap<>();
        controllers.put(GameCommand.START, StartController.getInstance());
        controllers.put(GameCommand.MOVE, MoveController.getInstance());
        controllers.put(GameCommand.END, EndController.getInstance());
        controllers.put(GameCommand.STATUS, StatusController.getInstance());
        controllers.put(GameCommand.LOGIN, LoginController.getInstance());
        controllers.put(GameCommand.SIGNUP, SignUpController.getInstance());

        return controllers;
    }

    public void init() {
        ensureLoggedIn();
        outputView.printInitialMessage();
        processNextRequest();
    }

    private void ensureLoggedIn() {
        outputView.printLoginMessage();
        Request request = readRequest();
        GameCommand gameCommand = request.getGameCommand();
        if (GameCommand.LOGIN == gameCommand || GameCommand.SIGNUP == gameCommand) {
            Response response = controllers.get(gameCommand).execute(request);
            if (response.getType() == ResponseType.FAIL) {
                outputView.printCommandError(response.getCause());
                ensureLoggedIn();
            }
            return;
        }
        outputView.ensureLoginMessage();
        ensureLoggedIn();
    }

    private void processNextRequest() {
        Request request = readRequest();
        Controller controller = controllers.get(request.getGameCommand());
        if (request.getGameCommand() == GameCommand.LOGIN || request.getGameCommand() == GameCommand.SIGNUP) {
            outputView.printInvalidCommand();
            processNextRequest();
        }
        Response response = controller.execute(request);
        handleResponse(response);
    }

    private void handleResponse(Response response) {
        ResponseType type = response.getType();
        if (type == ResponseType.MOVE || type == ResponseType.START) {
            outputView.printBoard(response.getBoard());
            processNextRequest();
        }
        if (type == ResponseType.FAIL) {
            outputView.printCommandError(response.getCause());
            processNextRequest();
        }
        if (type == ResponseType.STATUS) {
            outputView.printResult(response.getResultDto());
            processNextRequest();
        }
    }

    private Request readRequest() {
        return repeat(() -> new Request(inputView.inputGameCommand()));
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
