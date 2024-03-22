package controller;

import exception.CustomException;
import java.util.Collections;
import java.util.List;
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

    public List<String> getCommand() {
        List<String> command = Collections.emptyList();
        while (command.isEmpty()) {
            command = readCommand();
        }
        return command;
    }

    private List<String> readCommand() {
        try {
            return readLine();
        } catch (CustomException exception) {
            outputView.printException(exception);
        }
        return Collections.emptyList();
    }

    private List<String> readLine() {
        List<String> input = inputView.readCommandList();
        for (String value : input) {
            Command.validate(value);
        }
        return input;
    }
}
