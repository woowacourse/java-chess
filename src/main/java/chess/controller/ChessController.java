package chess.controller;

import static chess.ChessGameCommand.COMMAND_INDEX;
import static chess.ChessGameCommand.DEFAULT_COMMAND_SIZE;
import static chess.ChessGameCommand.EMPTY;
import static chess.ChessGameCommand.END;
import static chess.ChessGameCommand.FROM_INDEX;
import static chess.ChessGameCommand.MOVE;
import static chess.ChessGameCommand.MOVE_COMMAND_SIZE;
import static chess.ChessGameCommand.START;
import static chess.ChessGameCommand.STATUS;
import static chess.ChessGameCommand.TO_INDEX;

import chess.ChessGameCommand;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {
    private final Board board;
    private final Map<ChessGameCommand, ChessGameAction> commandMapper;

    public ChessController() {
        this.board = new BoardFactory().createInitialBoard();
        this.commandMapper = Map.of(
                START, this::start,
                MOVE, this::movePiece,
                STATUS, this::showStatus,
                END, this::end
        );
    }

    public void run() {
        ChessGameCommand command = EMPTY;

        while (command.isPlayable()) {
            command = play();
            if (board.isEnd()) {
                command = END;
                OutputView.printWinner(board.winner());
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
        validateCommandsSize(commands, DEFAULT_COMMAND_SIZE);
        OutputView.printBoard(board.board());
    }

    private void movePiece(final List<String> commands) {
        validateCommandsSize(commands, MOVE_COMMAND_SIZE);
        Position from = searchPosition(commands.get(FROM_INDEX));
        Position to = searchPosition(commands.get(TO_INDEX));

        board.move(from, to);
        OutputView.printBoard(board.board());
    }

    private void showStatus(final List<String> commands) {
        validateCommandsSize(commands, DEFAULT_COMMAND_SIZE);

        OutputView.printScore(board.calculateScore());
    }

    private void end(final List<String> commands) {
        validateCommandsSize(commands, DEFAULT_COMMAND_SIZE);

        if (!board.isEnd()) {
            OutputView.printScore(board.calculateScore());
        }

        OutputView.printWinner(board.winner());
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
