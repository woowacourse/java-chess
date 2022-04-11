package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.service.DaoService;

public class Start extends Command {
    private static final String ERROR_GAME_IS_NOT_END = "[ERROR]아직 게임 안끝났어!";

    public Start(List<String> commands) {
        super(commands, COMMAND_NOT_MOVE_FORMAT_SIZE);
    }

    @Override
    public ChessGame execute(ChessGame game, DaoService daoService) {
        if (game != null) {
            throw new IllegalArgumentException(ERROR_GAME_IS_NOT_END);
        }
        game = new ChessGame();
        daoService.save(game);
        return game;
    }
}
