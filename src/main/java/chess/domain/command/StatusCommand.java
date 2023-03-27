package chess.domain.command;

import java.util.List;

public class StatusCommand implements Command {
    public StatusCommand(List<String> commands) {
        validateCommandsLength(commands);
    }

    @Override
    public void validateCommandsLength(List<String> commands) {
        if (commands.size() != 1) {
            throw new IllegalArgumentException("잘못된 명령어 입니다. 팀별 점수 출력을 원하신다면 status를 입력해주세요.");
        }
    }
}
