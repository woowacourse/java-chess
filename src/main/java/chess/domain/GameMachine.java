package chess.domain;

import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class GameMachine {

    public void run() {
        InputView.announceStart();
        Board board = null;
        String command = "";
        do {
            command = InputView.requestCommand();
            board = play(board, command);
        } while (!command.equals("end"));
    }

    private Board play(Board board, String command) {
        if (command.trim().equals("start")) {
            board = new Board(new BoardInitiator());
            OutputView.printBoard(board);
        }
        if (command.trim().startsWith("move")) {
            List<String> commands = Arrays.asList(command.split(" "));
            movePiece(board, commands);
        }
        return board;
    }

    private void movePiece(Board board, List<String> commands) {
        if (board == null) {
            OutputView.announceNotStarted();
            return;
        }
        if (commands.size() != 3) {
            OutputView.announceWrongMoveCommand();
            return;
        }
        board.move(commands.get(1), commands.get(2));
        OutputView.printBoard(board);
    }
}
