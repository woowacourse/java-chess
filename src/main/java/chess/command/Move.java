package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.position.Square;
import chess.service.DaoService;

public class Move extends Command {
    private static final String ERROR_MESSAGE_POSITION_FORMAT = "[ERROR] 위치의 포맷을 지켜서 입력하세요.";
    private static final String ERROR_GAME_IS_OVER = "[ERROR] 지금은 못 움직여!";

    private static final int COMMAND_MOVE_FORMAT_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int POSITION_SIZE = 2;

    private final Square source;
    private final Square target;

    public Move(List<String> commands) {
        super(commands, COMMAND_MOVE_FORMAT_SIZE);
        validatePositionFormat(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        this.source = new Square(commands.get(SOURCE_INDEX));
        this.target = new Square(commands.get(TARGET_INDEX));
    }

    @Override
    public ChessGame execute(ChessGame game, DaoService daoService) {
        checkGameStarted(game);
        if (game.isKingDie()) {
            throw new IllegalArgumentException(ERROR_GAME_IS_OVER);
        }
        game.move(source, target);
        daoService.save(game);
        return game;
    }

    private void validatePositionFormat(String source, String target) {
        if (source.length() != POSITION_SIZE || target.length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_FORMAT);
        }
    }
}
