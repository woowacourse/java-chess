package chess.view;

public class CommandException extends IllegalArgumentException {

    public CommandException() {
        super("올바르지 않은 커멘드입니다.");
    }
}
