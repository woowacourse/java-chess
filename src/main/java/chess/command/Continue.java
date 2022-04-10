package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.service.DaoService;

public class Continue extends Command {
    private static final String ERROR_MESSAGE_IMPOSSIBLE_COMMAND = "[ERROR] 지금은 앙댕! 혼난다??\n";
    private static final String ERROR_NO_SAVE_GAME = "[ERROR] 저장된 게임이 없습니다";

    public Continue(List<String> commands) {
        super(commands, COMMAND_NOT_MOVE_FORMAT_SIZE);
    }

    @Override
    public ChessGame execute(ChessGame game, DaoService daoService) {
        if (game != null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
        game = daoService.find();

        if (game == null) {
            throw new IllegalArgumentException(ERROR_NO_SAVE_GAME);
        }

        return game;
    }
}
