package chess.controller;

import static chess.controller.ChessGameCommand.CLEAR;
import static chess.controller.ChessGameCommand.EMPTY;
import static chess.controller.ChessGameCommand.END;
import static chess.controller.ChessGameCommand.MOVE;
import static chess.controller.ChessGameCommand.MOVE_SOURCE_INDEX;
import static chess.controller.ChessGameCommand.MOVE_TARGET_INDEX;
import static chess.controller.ChessGameCommand.START;
import static chess.controller.ChessGameCommand.STATUS;

import chess.db.FixedConnectionPool;
import chess.db.JdbcTemplate;
import chess.dto.MoveDto;
import chess.repository.ChessDao;
import chess.repository.ChessJdbcDao;
import chess.service.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {

    private final Map<ChessGameCommand, ChessGameAction> commandMapper = new EnumMap<>(ChessGameCommand.class);

    public ChessGameController() {
        this.commandMapper.putAll(mappingCommand());
    }

    private Map<ChessGameCommand, ChessGameAction> mappingCommand() {
        return Map.of(
                START, (chessGame, commands) -> start(chessGame),
                MOVE, this::move,
                STATUS, (chessGame, ignore) -> status(chessGame),
                CLEAR, (chessGame, ignore) -> clear(chessGame),
                END, ChessGameAction.EMPTY
        );
    }

    public void run() {
        OutputView.printGameStart();
        final ChessDao chessDao = new ChessJdbcDao(new JdbcTemplate(FixedConnectionPool.getInstance()));
        final ChessGame chessGame = new ChessGame(chessDao);
        ChessGameCommand command = EMPTY;
        while (command != END) {
            command = play(chessGame);
            command = checkGameOver(chessGame, command);
        }
        OutputView.printGameEnd();
    }

    private ChessGameCommand play(final ChessGame chessGame) {
        try {
            final List<String> commands = InputView.readCommand();
            final ChessGameCommand command = ChessGameCommand.from(commands);
            command.validateCommandsSize(commands);
            final ChessGameAction chessGameAction = commandMapper.get(command);
            chessGameAction.execute(chessGame, commands);
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

    private ChessGameCommand checkGameOver(final ChessGame chessGame, final ChessGameCommand command) {
        if (chessGame.isGameOver()) {
            OutputView.printStatus(chessGame.getResult());
            return END;
        }
        return command;
    }
}
