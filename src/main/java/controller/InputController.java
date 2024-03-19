package controller;

import model.Command;
import view.InputView;
import view.OutputView;

public class InputController {

    private final InputView inputView;
    private final OutputView outputView;

    public InputController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Command getCommand() {
        Command command = null;
        while (command == null) {
            command = readCommand();
        }
        return command;
    }

    private Command readCommand() {
        try {
            return Command.from(inputView.readCommand());
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
        }
        return null;
    }
}
