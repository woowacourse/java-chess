package chess.view.command;

public class End implements Command {

    @Override
    public Command run(final String command) {
        throw new IllegalStateException("게임이 종료된 상태에서는 게임을 실행할 수 없습니다.");
    }
}
