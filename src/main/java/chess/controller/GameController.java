package chess.controller;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();
    private static final String END_COMMAND = "end";
    private static final String START_COMMAND = "start";

    private Board board = null;

    public void run() {
        OUTPUT_VIEW.printGameStart();
        play();
    }

    private void play() {
        try {
            playTurn();
        } catch (RuntimeException exception) {
            OUTPUT_VIEW.printException(exception);
            play();
        }
    }

    private void playTurn() {
        while (true) {
            String command = INPUT_VIEW.readCommand();
            if (END_COMMAND.equalsIgnoreCase(command)) {
                return;
            }
            if (START_COMMAND.equalsIgnoreCase(command)) {
                createBoard();
                continue;
            }
            move(command);
        }
    }

    private void move(String command) {
        if (board == null) {
            throw new IllegalStateException("아직 게임이 시작되지 않았습니다.");
        }
        String[] commands = command.split(" ");
        Location source = Location.of(commands[1]);
        Location target = Location.of(commands[2]);
        board.tryMove(source, target);
        OUTPUT_VIEW.printBoard(board.getBoard());
    }

    private void createBoard() {
        board = new Board();
        OUTPUT_VIEW.printBoard(board.getBoard());
    }
}
