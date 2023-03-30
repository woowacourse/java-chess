package chess.domain.command;

import java.util.List;

public class StartCommand implements Command {
    public StartCommand(List<String> commands) {
        validateCommandsLength(commands);
    }

    @Override
    public void validateCommandsLength(List<String> commands) {
        if (commands.size() != 1) {
            throw new IllegalArgumentException("잘못된 명령어 입니다. 게임 시작을 원하신다면 start를 입력해주세요.");
        }
    }
}
