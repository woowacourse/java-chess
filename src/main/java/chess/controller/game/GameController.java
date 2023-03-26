package chess.controller.game;

import static chess.controller.game.GameCommand.EMPTY;
import static chess.controller.game.GameCommand.END;
import static chess.controller.game.GameCommand.MOVE;
import static chess.controller.game.GameCommand.MOVE_SOURCE_INDEX;
import static chess.controller.game.GameCommand.MOVE_TARGET_INDEX;
import static chess.controller.game.GameCommand.STATUS;

import chess.controller.CommandMapper;
import chess.controller.Controller;
import chess.controller.session.RoomSession;
import chess.controller.session.UserSession;
import chess.dto.MoveDto;
import chess.service.GameService;
import chess.view.input.GameInputView;
import chess.view.output.GameOutputView;
import java.util.List;
import java.util.Map;

public class GameController implements Controller {
    private final GameInputView inputView;
    private final GameOutputView outputView;
    private final GameService gameService;
    private final CommandMapper<GameCommand, GameAction> commandMapper;

    public GameController(
            final GameInputView inputView,
            final GameOutputView outputView,
            final GameService gameService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
        this.commandMapper = new CommandMapper<>(mappingCommand());
    }

    private Map<GameCommand, GameAction> mappingCommand() {
        return Map.of(
                MOVE, this::move,
                STATUS, (gameService, ignore) -> status(gameService),
                END, GameAction.EMPTY
        );
    }

    @Override
    public void run() {
        outputView.printGameStart();
        gameService.initialize(RoomSession.getId());
        outputView.printBoard(gameService.getResult(RoomSession.getId()));
        GameCommand command = EMPTY;
        while (command != END) {
            command = play(gameService);
            command = checkGameOver(gameService, command);
        }
        outputView.printGameEnd();
    }

    private GameCommand play(final GameService gameService) {
        try {
            final List<String> commands = inputView.readCommand(UserSession.getName(), RoomSession.getName());
            final GameCommand command = GameCommand.from(commands);
            command.validateCommandsSize(commands);
            final GameAction gameAction = commandMapper.getValue(command);
            gameAction.execute(gameService, commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printException(e.getMessage());
            return EMPTY;
        }
    }

    private void move(final GameService gameService, final List<String> commands) {
        final MoveDto moveDto = new MoveDto(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
        gameService.move(moveDto, RoomSession.getId());
        outputView.printBoard(gameService.getResult(RoomSession.getId()));
    }

    private void status(final GameService gameService) {
        outputView.printStatus(gameService.getResult(RoomSession.getId()));
    }

    private GameCommand checkGameOver(final GameService gameService, final GameCommand command) {
        if (gameService.isGameOver(RoomSession.getId())) {
            outputView.printStatus(gameService.getResult(RoomSession.getId()));
            return END;
        }
        return command;
    }
}
