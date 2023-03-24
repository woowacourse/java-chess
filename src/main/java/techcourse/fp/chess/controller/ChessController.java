package techcourse.fp.chess.controller;

import java.util.EnumMap;
import java.util.Map;
import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.dto.BoardResponse;
import techcourse.fp.chess.dto.CommandRequest;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, CommandRunner> commandMapper = new EnumMap<>(Command.class);

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;

        commandMapper.put(Command.MOVE, this::move);
        commandMapper.put(Command.END, CommandRunner.end);
    }

    public void run() {
        outputView.printInitialMessage();

        final Command command = getInitialCommand();

        if (command == Command.START) {
            startGame();
        }

        outputView.printEndMessage();
    }

    private Command getInitialCommand() {
        try {
            return Command.createStartOrEnd(inputView.readStartOrEndCommand());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getInitialCommand();
        }
    }

    private void startGame() {
        final Board board = BoardFactory.generate();
        outputView.printBoard(BoardResponse.create(board.getBoard()));
        Command command = Command.EMPTY;
        while (command != Command.END) {
            command = play(board);
        }
    }

    private Command play(final Board board) {
        try {
            final CommandRequest commandRequest = inputView.readMoveOrEndCommand();
            Command command = Command.createMoveOrEnd(commandRequest.getMessage());

            final CommandRunner commandRunner = commandMapper.get(command);
            commandRunner.execute(commandRequest, board);

            if (board.isGameEnd()) {
                outputView.printWinningMessage(board.findWinner());
                return Command.END;
            }

            return command;
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return Command.EMPTY;
        }
    }

    private void move(final CommandRequest commandRequest, Board board) {
        try {
            board.move(commandRequest.getSource(), commandRequest.getTarget());
            outputView.printBoard(BoardResponse.create(board.getBoard()));
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }
}
