package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.Result;
import chess.service.DaoService;

public abstract class Command {

    private static final String ERROR_MESSAGE_LACK_INFORMATION = "[ERROR] 부족해잉~ 더줘잉~";
    private static final String ERROR_MESSAGE_TMI = "[ERROR] 투 머치 인포메이션~ㅋ";
    private static final String ERROR_GAME_NOT_START_YET = "[ERROR]게임부터 시작하지지?!";

    protected static final int COMMAND_NOT_MOVE_FORMAT_SIZE = 1;
    private static final int COMMAND_INDEX = 0;

    private final CommandType commandType;

    public Command(List<String> commands, int size) {
        validateCommandFormatSize(commands, size);
        this.commandType = CommandType.from(commands.get(COMMAND_INDEX));
    }

    private void validateCommandFormatSize(List<String> commands, int size) {
        if (commands.size() < size) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LACK_INFORMATION);
        }

        if (commands.size() > size) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TMI);
        }
    }

    public abstract ChessGame execute(ChessGame game, DaoService daoService);

    protected void checkGameStarted(ChessGame game) {
        if (game == null) {
            throw new IllegalArgumentException(ERROR_GAME_NOT_START_YET);
        }
    }

    public Result getResult() {
        return null;
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
