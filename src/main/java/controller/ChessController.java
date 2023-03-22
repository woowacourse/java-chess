package controller;


import static controller.Command.COMMAND_INDEX;
import static controller.Command.CURRENT_SQUARE_INDEX;
import static controller.Command.EMPTY;
import static controller.Command.END;
import static controller.Command.MOVE;
import static controller.Command.MOVE_COMMAND_LENGTH;
import static controller.Command.STANDARD_COMMAND_LENGTH;
import static controller.Command.START;
import static controller.Command.TARGET_SQUARE_INDEX;

import java.util.EnumMap;
import java.util.List;

import service.ChessService;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final ChessService chessService;
    private final EnumMap<Command, Action> actions = new EnumMap<>(Command.class);

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
        actions.put(START, this::start);
        actions.put(MOVE, this::move);
        actions.put(END, this::end);
    }

    public void run() {
        OutputView.printChessInfo();
        Command command = EMPTY;
        while (command != END) {
            command = play();
        }
    }

    private Command play() {
        try {
            List<String> inputs = InputView.requestCommand();
            Command command = Command.find(inputs.get(COMMAND_INDEX));
            Action action = actions.get(command);
            action.execute(inputs);
            return refreshCommandByResult(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e);
            return EMPTY;
        }
    }

    private Command refreshCommandByResult(Command command) {
        if (chessService.isFinished()) {
            return END;
        }
        return command;
    }

    private void start(List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
        chessService.setUp();
        OutputView.printChessBoard(chessService.getChessBoard());
    }

    private void move(List<String> inputs) {
        Command.validateCommandLength(inputs.size(), MOVE_COMMAND_LENGTH);
        String currentSquareInput = inputs.get(CURRENT_SQUARE_INDEX);
        String targetSquareInput = inputs.get(TARGET_SQUARE_INDEX);
        chessService.move(currentSquareInput, targetSquareInput);
        OutputView.printChessBoard(chessService.getChessBoard());
    }

    private void end(List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
        chessService.end();
    }
}
