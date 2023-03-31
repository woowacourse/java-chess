package chess.controller.room;

import chess.controller.Controller;
import chess.controller.GameCommand;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.controller.login.LoginSession;
import chess.dao.GameDao;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RoomController {
    private final static RoomController INSTANCE = new RoomController();
    private final Map<GameCommand, Controller> controllers;
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    private RoomController() {
        controllers = makeControllers();
    }

    public static RoomController getInstance() {
        return INSTANCE;
    }

    private Map<GameCommand, Controller> makeControllers() {
        Map<GameCommand, Controller> controllers = new HashMap<>();
        controllers.put(GameCommand.CREATE, CreateController.getInstance());
        controllers.put(GameCommand.RESUME, ResumeController.getInstance());
        return controllers;
    }

    public void enterRoom() {
        printLoginUserRoom();
        Request request = readLoginRequest();
        Controller controller = controllers.get(request.getGameCommand());
        Response response = controller.execute(request);
        if (response.getType() == ResponseType.FAIL) {
            outputView.printFailReason(response.getCause());
            enterRoom();
        }
    }

    private void printLoginUserRoom() {
        List<String> roomNames = GameDao.getGameNameOf(LoginSession.getCurrentLoginId());
        outputView.printCurrentPlayRoom(roomNames);
    }

    private Request readLoginRequest() {
        Request request = repeat(() -> new Request(inputView.inputGameCommand()));
        GameCommand gameCommand = request.getGameCommand();
        if (gameCommand == GameCommand.RESUME || gameCommand == GameCommand.CREATE) {
            return request;
        }
        outputView.ensureRoomMessage();
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
