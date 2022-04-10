package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.Result;
import chess.service.DaoService;

public class Status extends Command {
    private static final String ERROR_GAME_IS_NOT_END = "[ERROR]아직 게임 안끝났어!";
    private Result result;

    public Status(List<String> commands) {
        super(commands, COMMAND_NOT_MOVE_FORMAT_SIZE);
    }

    @Override
    public ChessGame execute(ChessGame game, DaoService daoService) {
        checkGameStarted(game);
        if (!game.isKingDie()) {
            throw new IllegalArgumentException(ERROR_GAME_IS_NOT_END);
        }
        result = game.saveStatus();
        daoService.remove();
        return null;
    }

    @Override
    public Result getResult() {
        return result;
    }
}
