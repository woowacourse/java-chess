package chess.controller;

import static chess.view.Command.END;
import static chess.view.Command.MOVE;
import static chess.view.Command.RESET;
import static chess.view.Command.SCORE;
import static chess.view.Command.START;
import static chess.view.InputView.readCommand;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printError;
import static chess.view.OutputView.printFinishMessage;
import static chess.view.OutputView.printGameStart;
import static chess.view.OutputView.printScores;

import chess.dao.ChessGameDao;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.view.Command;
import java.util.List;
import java.util.function.Supplier;

public final class MainController {

    private static final int COMMAND_INDEX = 0;
    private static final int CURRENT_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;

    private final ChessGameDao chessGameDao;

    public MainController(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        printGameStart();
        Command command = repeatUntilValidAction(this::getValidCommand);

        if (command == START) {
            startGame();
        }
        if (command == RESET) {
            chessGameDao.deleteAll();
            startGame();
        }
        printFinishMessage();
    }

    private Command getValidCommand() {
        List<String> inputs = readCommand();
        return Command.of(inputs.get(COMMAND_INDEX));
    }

    private void startGame() {
        final Board board = generateBoard();
        chessGameDao.insert(board);
        printBoard(board.getBoard());
        while (repeatUntilValidAction(() -> playChess(board)));
        printScores(board.scores());
    }

    private Board generateBoard() {
        Board board = chessGameDao.findBoard();

        if (board == null) {
            return Board.create();
        }
        return board;
    }

    private boolean playChess(final Board board) {
        chessGameDao.updateBoard(board);

        List<String> inputs = readCommand();
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        return executeCommand(board, inputs, command);
    }

    private boolean executeCommand(final Board board, final List<String> inputs, final Command command) {
        if (command == END) {
            return false;
        }
        if (command == START || command == RESET) {
            throw new IllegalArgumentException("이미 게임을 실행중입니다. 다른 명령어를 입력해주세요.");
        }
        if (command == MOVE) {
            validateInputSize(inputs);
            movePiece(board, inputs);
            return board.isKingAlive();
        }
        if (command == SCORE) {
            printScores(board.scores());
        }
        return true;
    }

    private void movePiece(final Board board, final List<String> inputs) {
        Position currentPosition = Position.toPosition(inputs.get(CURRENT_POSITION_INDEX));
        Position targetPosition = Position.toPosition(inputs.get(TARGET_POSITION_INDEX));

        board.movePiece(currentPosition, targetPosition);
        printBoard(board.getBoard());

        chessGameDao.updateBoard(board);
    }

    private void validateInputSize(final List<String> inputs) {
        if (inputs.size() == MOVE_COMMAND_SIZE) {
            return;
        }
        throw new IllegalArgumentException("입력이 잘못되었습니다. 다시 입력해주세요.");
    }

    private <T> T repeatUntilValidAction(final Supplier<T> reader) {
        try {
            return reader.get();
        } catch (IllegalArgumentException e) {
            printError(e.getMessage());
            return repeatUntilValidAction(reader);
        }
    }
}
