package chess.controller;

public class ExceptionCommand implements Command {
    @Override
    public void execute(String command) {
        throw new IllegalArgumentException("존재하지 않는 명령입니다.");
    }
}
