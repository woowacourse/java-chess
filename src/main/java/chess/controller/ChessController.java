package chess.controller;

import static chess.ChessGameCommand.COMMAND_INDEX;
import static chess.ChessGameCommand.DEFAULT_COMMAND_SIZE;
import static chess.ChessGameCommand.EMPTY;
import static chess.ChessGameCommand.END;
import static chess.ChessGameCommand.FROM_INDEX;
import static chess.ChessGameCommand.MOVE;
import static chess.ChessGameCommand.MOVE_COMMAND_SIZE;
import static chess.ChessGameCommand.START;
import static chess.ChessGameCommand.TO_INDEX;

import chess.ChessGameCommand;
import chess.board.Board;
import chess.board.BoardFactory;
import chess.position.Position;
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
                END, this::end
        );
    }

    public void run() {
        ChessGameCommand command = EMPTY;

        while (command.isPlayable()) {
            command = play();
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
        if (commands.size() != DEFAULT_COMMAND_SIZE) {
            throw new IllegalArgumentException("start 하나만 입력해주셈!");
        }
        OutputView.printBoard(board.board());
    }

    private void movePiece(final List<String> commands) {
        if (commands.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("move b2 b3 이런식으로 입력해주셈!");
        }
        Position from = searchPosition(commands.get(FROM_INDEX));
        Position to = searchPosition(commands.get(TO_INDEX));

        board.move(from, to);
        OutputView.printBoard(board.board());
    }

    private void end(final List<String> strings) {
        if (strings.size() != DEFAULT_COMMAND_SIZE) {
            throw new IllegalArgumentException("end 하나만 입력해 주셈!");
        }
    }

    private Position searchPosition(final String command) {
        int fromFile = command.charAt(0) - 'a' + 1;
        int fromRank = command.charAt(1) - '0';

        return new Position(fromFile, fromRank);
    }
}
