package techcourse.fp.chess.controller;

import java.util.EnumMap;
import java.util.Map;
import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.dto.BoardDto;
import techcourse.fp.chess.dto.CommandRequest;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Board board = BoardFactory.generate();
    private final Map<Command, CommandRunner> commandMapper = new EnumMap<>(Command.class);

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;

        commandMapper.put(Command.START, this::start);
        commandMapper.put(Command.MOVE, this::move);
        commandMapper.put(Command.END, this::end);
    }

    public void run() {
        outputView.printInitialMessage();

        Command command = Command.EMPTY;
        while (command != Command.END) {
            command = play();
        }
    }

    private Command play() {
        try {
            final CommandRequest commandRequest = inputView.readCommand();
            Command command = Command.from(commandRequest.getMessage());

            final CommandRunner commandRunner = commandMapper.get(command);
            commandRunner.execute(commandRequest);

            return command;
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return Command.EMPTY;
        }
    }

    private void start(CommandRequest commandRequest) {
        try {
            Board board = BoardFactory.generate();
            outputView.printBoard(BoardDto.create(board.getBoard()));
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private void move(final CommandRequest commandRequest) {
        try {
            board.move(commandRequest.getSource(), commandRequest.getTarget());
            outputView.printBoard(BoardDto.create(board.getBoard()));
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private void end(final CommandRequest commandRequest) {
        outputView.printEndMessage();
    }
}
