package techcourse.fp.chess.controller;

import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;
import techcourse.fp.chess.dto.PieceDto;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Board board = BoardFactory.generate();

        outputView.printInitialMessage();
        final Command initCommand = getInitCommand();

        if (initCommand.isStart()) {
            outputView.printBoard(PieceDto.create(board.getBoard()));
            move(board);
        }

        outputView.printEndMessage();

    }

    private Command getInitCommand() {
        try {
            final String rawCommand = inputView.readInitCommand();
            return Command.createInitMessage(rawCommand);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getInitCommand();
        }
    }

    private void move(final Board board) {
        try {
            String[] commands = inputView.readCommand();

            while (!Command.createMoveOrEndMessage(commands[0]).isEnd()) {
                board.move(parseToPosition(commands[1]), parseToPosition(commands[2]));
                outputView.printBoard(PieceDto.create(board.getBoard()));
                commands = inputView.readCommand();
            }
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            move(board);
        }
    }

    private Position parseToPosition(final String command) {

        final int fileOrder = command.charAt(0) - 96;
        final int rankOrder = command.charAt(1) - '0';
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }
}
