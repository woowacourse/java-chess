package chess.view.command;

public class Ready implements Command {

    @Override
    public Command run(String command) {
        if (!command.equals("start")) {
            throw new IllegalArgumentException("게임이 시작되지 않아 다른 명령을 실행할 수 없습니다.");
        }
        return null;
    }
}
