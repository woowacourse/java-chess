package chess.domain.command;

public class InvalidCommand implements Command {

    @Override
    public void handle(final String input) {
        throw new IllegalArgumentException("알 수 없는 커맨드입니다.");
    }

    @Override
    public boolean isUsable(final String input) {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
