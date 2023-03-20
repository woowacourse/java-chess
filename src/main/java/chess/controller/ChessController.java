package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

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
            outputView.printBoard(BoardDto.create(board.getBoard()));
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
            List<String> commands = inputView.readCommand();
            while (!Command.createMoveOrEndMessage(commands.get(0)).isEnd()) {
                board.move(parseToPosition(commands.get(1)), parseToPosition(commands.get(2)));
                outputView.printBoard(BoardDto.create(board.getBoard()));
                commands = inputView.readCommand();
            }
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            move(board);
        }
    }

    private Position parseToPosition(final String command) {
        final int fileOrder = getFileOrder(command);
        final int rankOrder = getRankOrder(command);
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    private int getFileOrder(final String command) {
        final int charToIntDifference = 96;
        return command.charAt(0) - charToIntDifference;
    }

    private int getRankOrder(final String command) {
        final char charToIntDifference = '0';
        return command.charAt(1) - charToIntDifference;
    }
}
