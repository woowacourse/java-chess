package command;

import static command.PlayCommand.END;
import static command.PlayCommand.MOVE;
import static command.PlayCommand.SAVE;
import static command.PlayCommand.STATUS;

import domain.position.Position;
import domain.position.Positions;
import java.util.List;

public final class PlayCommandParser {
    private static final int COMMAND_INDEX = 0;
    private static final int PARAMETER_1 = 1;
    private static final int PARAMETER_2 = 2;

    private final PlayCommand playCommand;
    private final List<String> parameter;

    private PlayCommandParser(final PlayCommand playCommand, final List<String> parameter) {
        this.playCommand = playCommand;
        this.parameter = parameter;
    }

    public static PlayCommandParser parse(final List<String> commands) {
        if (END.equals(PlayCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 1) {
            return new PlayCommandParser(END, List.of());
        }
        if (STATUS.equals(PlayCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 1) {
            return new PlayCommandParser(STATUS, List.of());
        }
        if (SAVE.equals(PlayCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 1) {
            return new PlayCommandParser(SAVE, List.of());
        }
        if (MOVE.equals(PlayCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 3) {
            return new PlayCommandParser(MOVE, List.of(commands.get(PARAMETER_1), commands.get(PARAMETER_2)));
        }

        throw new IllegalArgumentException("명령은 move source위치 target위치 또는 end, save이어야 합니다.");
    }

    public PlayCommand getPlayCommand() {
        return playCommand;
    }

    public Position getSource() {
        return Positions.from(parameter.get(PARAMETER_1 - 1));
    }

    public Position getDestination() {
        return Positions.from(parameter.get(PARAMETER_2 - 1));
    }
}
