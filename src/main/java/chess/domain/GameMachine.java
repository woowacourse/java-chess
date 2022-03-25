package chess.domain;

import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class GameMachine {

    public static final String MOVE_DELIMITER = " ";
    public static final int MOVE_COMMAND_SIZE = 3;
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;

    public void run() {
        InputView.announceStart();
        Board board = null;
        String command = "";
        do {
            command = InputView.requestCommand();
            board = play(board, command);
        } while (!Command.isEnd(command) && !board.isEnd());
    }

    private Board play(Board board, String command) {
        if (Command.isStart(command)) {
            board = new Board(new BoardInitiator());
            OutputView.printBoard(board);
        }
        if (Command.isMove(command)) {
            List<String> commands = Arrays.asList(command.split(MOVE_DELIMITER));
            movePiece(board, commands);
        }
        return board;
    }

    private void movePiece(Board board, List<String> commands) {
        if (board == null) {
            OutputView.announceNotStarted();
            return;
        }
        if (commands.size() != MOVE_COMMAND_SIZE) {
            OutputView.announceWrongMoveCommand();
            return;
        }
        movePieceOnBoard(board, commands);
        OutputView.printBoard(board);
    }

    private void movePieceOnBoard(Board board, List<String> commands) {
        try {
            board.move(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        } catch (IllegalArgumentException e) {
            OutputView.announceBadMovement(e.getMessage());
        }
    }
}
