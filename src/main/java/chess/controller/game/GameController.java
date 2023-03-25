package chess.controller.game;

import static chess.controller.game.GameCommand.CLEAR;
import static chess.controller.game.GameCommand.EMPTY;
import static chess.controller.game.GameCommand.END;
import static chess.controller.game.GameCommand.MOVE;
import static chess.controller.game.GameCommand.MOVE_SOURCE_INDEX;
import static chess.controller.game.GameCommand.MOVE_TARGET_INDEX;
import static chess.controller.game.GameCommand.START;
import static chess.controller.game.GameCommand.STATUS;

import chess.controller.CommandMapper;
import chess.controller.Controller;
import chess.db.FixedConnectionPool;
import chess.db.JdbcTemplate;
import chess.dto.MoveDto;
import chess.repository.ChessDao;
import chess.repository.ChessJdbcDao;
import chess.service.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class GameController implements Controller {
    private final CommandMapper<GameCommand, GameAction> commandMapper;

    public GameController() {
        this.commandMapper = new CommandMapper<>(mappingCommand());
    }

    private Map<GameCommand, GameAction> mappingCommand() {
        return Map.of(
                START, (chessGame, commands) -> start(chessGame),
                MOVE, this::move,
                STATUS, (chessGame, ignore) -> status(chessGame),
                CLEAR, (chessGame, ignore) -> clear(chessGame),
                END, GameAction.EMPTY
        );
    }

    @Override
    public void run() {
        OutputView.printGameStart();
        final ChessDao chessDao = new ChessJdbcDao(new JdbcTemplate(FixedConnectionPool.getInstance()));
        final ChessGame chessGame = new ChessGame(chessDao);
        GameCommand command = EMPTY;
        while (command != END) {
            command = play(chessGame);
            command = checkGameOver(chessGame, command);
        }
        OutputView.printGameEnd();
    }

    private GameCommand play(final ChessGame chessGame) {
        try {
            final List<String> commands = InputView.readCommand();
            final GameCommand command = GameCommand.from(commands);
            command.validateCommandsSize(commands);
            final GameAction gameAction = commandMapper.getValue(command);
            gameAction.execute(chessGame, commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printException(e.getMessage());
            return EMPTY;
        }
    }

    private void start(final ChessGame chessGame) {
        if (chessGame.isInitialized()) {
            throw new IllegalArgumentException("이미 체스 게임이 시작되었습니다.");
        }
        chessGame.initialize();
        OutputView.printBoard(chessGame.getResult());
    }

    private void move(final ChessGame chessGame, final List<String> commands) {
        if (chessGame.isNotInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        final MoveDto moveDto = new MoveDto(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
        chessGame.move(moveDto);
        OutputView.printBoard(chessGame.getResult());
    }

    private void status(final ChessGame chessGame) {
        if (chessGame.isNotInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        OutputView.printStatus(chessGame.getResult());
    }

    private void clear(final ChessGame chessGame) {
        if (chessGame.isNotInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        chessGame.clear();
        OutputView.printGameClear();
    }

    private GameCommand checkGameOver(final ChessGame chessGame, final GameCommand command) {
        if (chessGame.isGameOver()) {
            OutputView.printStatus(chessGame.getResult());
            return END;
        }
        return command;
    }
}
