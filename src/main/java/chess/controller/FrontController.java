package chess.controller;

import chess.controller.request.Input;
import chess.controller.resposne.Output;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FrontController {

    private static final int REQUEST_TYPE_INDEX = 0;
    private static final int ORIGIN_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private final Input input;
    private final Output output;
    private final ChessController chessController;
    private final Map<String, Consumer<List<String>>> actions = Map.of(
            "start", this::start,
            "move", this::move,
            "end", this::end,
            "status", this::printStatus
    );

    public FrontController(ChessController chessController, Input input, Output output) {
        this.chessController = chessController;
        this.input = input;
        this.output = output;
    }

    public void run() {
        output.printInitialMessage();
        while (true) {
            List<String> request = input.inputGameCommand();
            String requestType = request.get(REQUEST_TYPE_INDEX);
            actions.get(requestType).accept(request);
        }
    }

    public void start(List<String> request) {
        chessController.start();
        chessController.printBoard();
    }

    public void move(List<String> request) {
        chessController.move(request.get(ORIGIN_INDEX), request.get(DESTINATION_INDEX));
        chessController.printBoard();
    }

    public void end(List<String> request) {
        chessController.finish();
    }

    public void printStatus(List<String> request) {
        chessController.printStatus();
    }
}
