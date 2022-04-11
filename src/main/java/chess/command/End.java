package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.service.DaoService;

public class End extends Command {

    public End(List<String> commands) {
        super(commands, COMMAND_NOT_MOVE_FORMAT_SIZE);
    }

    @Override
    public ChessGame execute(ChessGame game, DaoService daoService) {
        daoService.remove();
        return null;
    }

}
