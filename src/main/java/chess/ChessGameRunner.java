package chess;

import static chess.domain.gamecommand.CommandType.END;
import static chess.domain.gamecommand.CommandType.MOVE;
import static chess.domain.gamecommand.CommandType.PROMOTE;
import static chess.domain.gamecommand.CommandType.START;
import static chess.domain.gamecommand.CommandType.STATUS;
import static chess.view.output.OutputView.printCurrentBoard;
import static chess.view.output.OutputView.printStartMessage;

import chess.domain.board.Board;
import chess.domain.gamecommand.CommandStrategy;
import chess.domain.gamecommand.CommandType;
import chess.domain.gamecommand.Movement;
import chess.domain.gamecommand.Promotion;
import chess.domain.gamecommand.Start;
import chess.view.input.InputView;
import chess.view.output.OutputView;
import java.util.Map;

public class ChessGameRunner {

    private static final Map<CommandType, CommandStrategy> COMMAND_STRATEGY = Map.of(
            START, new Start(),
            MOVE, new Movement(),
            PROMOTE, new Promotion());

    public void run() {
        Board board = initializeBoard();
        play(board);
    }

    private Board initializeBoard() {
        printStartMessage();
        final String command = InputView.inputCommand();
        validateFirstCommand(command);
        return new Board();
    }

    private void validateFirstCommand(final String command) {
        if (CommandType.from(command) != START) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private void play(Board board) {
        CommandType commandType;
        do {
            printCurrentBoard(board.getPieces());
            String command = InputView.inputCommand();
            commandType = CommandType.from(command);
            board = execute(commandType, board, command);
        }
        while (commandType != END && !board.hasOneKing());
    }

    private Board execute(CommandType commandType, Board board, String command) {
        if (commandType == STATUS) {
            OutputView.printCurrentBoard(board.getPieces());
            return board;
        }
        if (commandType == END) {
            return board;
        }
        return COMMAND_STRATEGY.get(commandType)
                .play(board, command);
    }
}
