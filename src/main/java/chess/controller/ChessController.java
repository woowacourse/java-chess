package chess.controller;

import static chess.controller.ChessGameCommand.COMMAND_INDEX;
import static chess.controller.ChessGameCommand.DEFAULT_COMMAND_SIZE;
import static chess.controller.ChessGameCommand.EMPTY;
import static chess.controller.ChessGameCommand.END;
import static chess.controller.ChessGameCommand.FROM_INDEX;
import static chess.controller.ChessGameCommand.MOVE;
import static chess.controller.ChessGameCommand.MOVE_COMMAND_SIZE;
import static chess.controller.ChessGameCommand.START;
import static chess.controller.ChessGameCommand.STATUS;
import static chess.controller.ChessGameCommand.TO_INDEX;

import chess.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import dao.ChessGameDao;
import java.util.List;
import java.util.Map;

public final class ChessController {
    private final Map<ChessGameCommand, ChessGameAction> commandMapper;
    private final ChessGameDao chessGameDao;

    public ChessController(ChessGameDao chessGameDao) {
        this.commandMapper = Map.of(
                START, this::start,
                MOVE, this::movePiece,
                STATUS, this::showStatus,
                END, this::end
        );
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        ChessGame chessGame = chessGameDao.select();
        if (chessGame == null) {
            chessGame = new ChessGame(new BoardFactory().createInitialBoard());
            chessGameDao.save(chessGame);
        }

        OutputView.printStartMessage();
        ChessGameCommand command = EMPTY;

        while (command.isPlayable()) {
            command = play();
            if (chessGame.isEnd()) {
                command = END;
                OutputView.printWinner(chessGame.winner());
            }
        }
    }

    private ChessGameCommand play() {
        try {
            List<String> commands = InputView.readCommand();
            ChessGameCommand command = ChessGameCommand.from(commands.get(COMMAND_INDEX));
            commandMapper.get(command).execute(commands);
            return command;
        } catch (RuntimeException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return EMPTY;
        }
    }

    private void start(final List<String> commands) {
        ChessGame chessGame = chessGameDao.select();
        validateCommandsSize(commands, DEFAULT_COMMAND_SIZE);
        OutputView.printBoard(chessGame.board());
    }

    private void movePiece(final List<String> commands) {
        ChessGame chessGame = chessGameDao.select();
        validateCommandsSize(commands, MOVE_COMMAND_SIZE);
        Position from = searchPosition(commands.get(FROM_INDEX));
        Position to = searchPosition(commands.get(TO_INDEX));

        chessGame.move(from, to);
        OutputView.printBoard(chessGame.board());

        chessGameDao.update(chessGame);
    }

    private void showStatus(final List<String> commands) {
        ChessGame chessGame = chessGameDao.select();
        validateCommandsSize(commands, DEFAULT_COMMAND_SIZE);

        OutputView.printScore(chessGame.calculateScore());
    }

    private void end(final List<String> commands) {
        ChessGame chessGame = chessGameDao.select();
        validateCommandsSize(commands, DEFAULT_COMMAND_SIZE);

        if (!chessGame.isEnd()) {
            OutputView.printScore(chessGame.calculateScore());
            return;
        }

        OutputView.printWinner(chessGame.winner());
        chessGameDao.reset();
    }

    private static void validateCommandsSize(final List<String> commands, final int moveCommandSize) {
        if (commands.size() != moveCommandSize) {
            throw new IllegalArgumentException("명령을 형식에 맞게 입력해 주세요!");
        }
    }

    private Position searchPosition(final String command) {
        final List<String> positionCommands = List.of(command.split(""));
        validatePositionCommandsSize(positionCommands);
        return Position.of(positionCommands.get(0), positionCommands.get(1));
    }

    private static void validatePositionCommandsSize(final List<String> commands) {
        if (commands.size() != 2) {
            throw new IllegalArgumentException("a1 ~ h8까지 좌표를 입력해 주세요");
        }
    }
}
