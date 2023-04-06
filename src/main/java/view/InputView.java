package view;

import controller.YNCommand;

public class InputView {
    private final InputReader inputReader;

    public InputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public Command getGameCommand() {
        return new Command(inputReader.readInput());
    }

    public String getGameId() {
        return inputReader.readInput();
    }

    public YNCommand getYNCommand() {
        return YNCommand.from(inputReader.readInput());
    }
}
