package chess.model.command;

public class Start extends Command {

    public Start(String input) {
        super(input);
        validateStart(input);
    }

    private void validateStart(String input) {
        if (!Command.START.equals(input)) {
            throw new IllegalArgumentException("시작 시에는 start만 가능합니다.");
        }
    }

    @Override
    public Command turnState(String input) {
        if (Command.END.equals(input)) {
            return new End(input);
        }
        if (input.contains(Command.MOVE)) {
            return new Move(input);
        }
        throw new IllegalArgumentException("없는 명령어입니다.");
    }
}
