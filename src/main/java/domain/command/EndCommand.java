package domain.command;

import domain.game.Executable;
import java.util.List;

public class EndCommand implements Command {
    public EndCommand(List<String> arguments) {
        validate(arguments);
    }

    private void validate(List<String> arguments) {
        if (!arguments.isEmpty()) {
            throw new IllegalArgumentException("잘못된 end 명령어 입니다.");
        }
    }

    @Override
    public void execute(Executable executable) {
        executable.end();
    }
}
