package chess.domain.command;

public class ExceptionCommand implements Command {
    @Override
    public void execute(final String command) {
        throw new IllegalArgumentException("존재하지 않는 명령입니다.");
    }
}
