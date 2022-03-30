package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardInitiator;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class GameMachine {

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public void run() {
        InputView.announceStart();
        Board board = null;
        String command = "";
        do {
            command = InputView.requestCommand();
            board = play(board, command);
        } while (!Command.isEnd(command) && !board.isEnd());

        if (board != null) {
            OutputView.printFinalResult(board);
        }
    }

    private Board play(Board board, String command) {
        if (Command.isStart(command)) {
            board = new Board(new BoardInitiator());
            OutputView.printBoard(board);
        }
        if (Command.isMove(command)) {
            movePiece(board, Arrays.asList(command.split(MOVE_DELIMITER)));
        }
        if (Command.isStatus(command)) {
            OutputView.printScoreAndResult(board);
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
