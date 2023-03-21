package chess.controller;

import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    private final Map<GameCommand, Controller> controllers;
    private Board board;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        controllers = makeControllers(outputView);
        board = BoardGenerator.emtpyBoard();
    }

    private Map<GameCommand, Controller> makeControllers(OutputView outputView) {
        Map<GameCommand, Controller> controllers = new HashMap<>();
        controllers.put(GameCommand.START, new StartController(outputView));
        controllers.put(GameCommand.MOVE, new MoveController(outputView));
        controllers.put(GameCommand.END, new EndController(outputView));

        return controllers;
    }

    public void init() {
        outputView.printInitialMessage();
        playUntilEnd();
    }

    private void playUntilEnd() {
        RequestInfo requestInfo = readRequest();
        if (requestInfo.getGameCommand() == GameCommand.END) {
            return;
        }
        Controller controller = controllers.get(requestInfo.getGameCommand());
        board = controller.execute(requestInfo, board);
        playUntilEnd();
    }

    private RequestInfo readRequest() {
        return repeat(() -> new RequestInfo(inputView.inputGameCommand()));
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
