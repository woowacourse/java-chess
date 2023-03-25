package controller.command;

import view.InputView;
import view.OutputView;

import java.util.List;

public final class SystemBoot implements Command {

    protected enum SystemCommandType {
        START,
        END;
    }

    @Override
    public Command execute() {
        return readNextCommand();
    }

    @Override
    public Command readNextCommand() {
        SystemCommandType command = receiveSystemCommand();
        if (command == SystemCommandType.START) {
            return new Initialize();
        }
        return new SystemEnd();
    }

    private SystemCommandType receiveSystemCommand() {
        List<String> userInput = InputView.readUserInput();
        while (!isSystemCommands(userInput.get(0))) {
            OutputView.printNotSystemCommandMessage();
            userInput = InputView.readUserInput();
        }
        return SystemCommandType.valueOf(userInput.get(0).toUpperCase());
    }

    private boolean isSystemCommands(String input) {
        try {
            SystemCommandType.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
