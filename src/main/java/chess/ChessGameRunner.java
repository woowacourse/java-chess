package chess;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static chess.view.consoleview.input.CommandType.END;
import static chess.view.consoleview.input.CommandType.MOVE;
import static chess.view.consoleview.input.CommandType.START;
import static chess.view.consoleview.input.CommandType.STATUS;
import static chess.view.consoleview.input.InputView.inputCommand;
import static chess.view.consoleview.input.InputView.inputPromotionType;
import static chess.view.consoleview.output.OutputView.printCurrentBoard;
import static chess.view.consoleview.output.OutputView.printStartMessage;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.view.consoleview.input.CommandType;
import chess.view.consoleview.output.OutputView;

public class ChessGameRunner {

    private static final String COMMAND_DELIMITER = " ";
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public void run() {
        Board board = initializeBoard();
        play(board);
    }

    private Board initializeBoard() {
        printStartMessage();
        final String command = inputCommand();
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
            String command = inputCommand();
            commandType = CommandType.from(command);
            board = execute(commandType, board, command);
        }
        while (commandType != END && !board.hasOneKing());
    }

    private Board execute(final CommandType commandType,
                          final Board board,
                          final String command) {
        if (commandType == START) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
        if (commandType == STATUS) {
            OutputView.printScore(board.getTotalPoint(WHITE), board.getTotalPoint(BLACK));
        }
        if (commandType == END) {
            OutputView.printCurrentBoard(board.getPieces());
        }
        if (commandType == MOVE) {
            return movePiece(board, command);
        }
        return board;
    }

    private Board movePiece(Board board, final String command) {
        final Position sourcePosition = Position.from(
                command.split(COMMAND_DELIMITER)[SOURCE_POSITION_INDEX]);
        final Position targetPosition = Position.from(
                command.split(COMMAND_DELIMITER)[TARGET_POSITION_INDEX]);
        board = board.movePiece(sourcePosition, targetPosition);

        if (board.hasPromotionPawnIn(targetPosition)) {
            board = board.promotePawn(targetPosition, inputPromotionType());
        }
        return board;
    }
}
