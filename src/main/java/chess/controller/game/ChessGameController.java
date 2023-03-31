package chess.controller.game;

import chess.controller.Controller;
import chess.controller.GameCommand;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.controller.room.GameSession;
import chess.domain.dto.BoardDto;
import chess.domain.game.Game;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessGameController {
    private final static ChessGameController INSTANCE = new ChessGameController();

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    private final Map<GameCommand, Controller> controllers;

    private ChessGameController() {
        controllers = makeControllers();
    }

    public static ChessGameController getInstance() {
        return INSTANCE;
    }

    private Map<GameCommand, Controller> makeControllers() {
        Map<GameCommand, Controller> controllers = new HashMap<>();
        controllers.put(GameCommand.MOVE, MoveController.getInstance());
        controllers.put(GameCommand.END, EndController.getInstance());
        controllers.put(GameCommand.STATUS, StatusController.getInstance());

        return controllers;
    }

    public void playGame() {
        outputView.printInitialMessage();
        printCurrentBoard();
        playUntilEnd();
    }

    private void printCurrentBoard() {
        Game game = GameSession.getGame();
        outputView.printBoard(BoardDto.of(game.getBoard()));
    }

    private void playUntilEnd() {
        Request request = readRequest();
        Controller controller = controllers.get(request.getGameCommand());
        Response response = controller.execute(request);
        handleResponse(response);
        ResponseType type = response.getType();
        if (type == ResponseType.END || type == ResponseType.FINISH) {
            return;
        }
        playUntilEnd();
    }

    private void handleResponse(Response response) {
        ResponseType type = response.getType();
        if (type == ResponseType.MOVE) {
            outputView.printBoard(response.getBoard());
        }
        if (type == ResponseType.FAIL) {
            outputView.printFailReason(response.getCause());
        }
        if (type == ResponseType.STATUS) {
            outputView.printResult(response.getResultDto());
        }
    }

    private Request readRequest() {
        Request request = repeat(() -> new Request(inputView.inputGameCommand()));
        GameCommand gameCommand = request.getGameCommand();
        Controller controller = controllers.get(gameCommand);
        if (controller == null) {
            return readRequest();
        }
        return request;
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
