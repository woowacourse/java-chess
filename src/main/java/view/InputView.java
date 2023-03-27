package view;

public class InputView {
    private final InputReader inputReader;

    public InputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public Command getGameCommand() {
        return new Command(inputReader.readInput());
    }
}
