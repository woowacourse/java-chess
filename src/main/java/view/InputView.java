package view;

public class InputView {
    private final InputReader inputReader;

    public InputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public BootingCommand getGameBootingCommand() {
        return BootingCommand.findByCommand(inputReader.readInput());
    }
}
