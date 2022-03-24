package chess.command;

public class Move extends Command {

    public Move(String input) {
        super(input);
    }

    public String[] splitMove(){
        return input.split(" ");
    }
    @Override
    public Command turnState(String input) {
        if ("end".equals(input)) {
            return new End(input);
        }
        if (input.contains("move")) {
            return new Move(input);
        }
        throw new IllegalArgumentException("command has only move or end ");
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
