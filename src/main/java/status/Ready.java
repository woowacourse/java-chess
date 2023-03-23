package status;

import domain.board.Board;
import domain.board.InitialChessAlignment;
import view.InputView;
import view.OutputView;

public final class Ready implements Status {
    private static final String NOT_STARTED = "게임을 먼저 시작해야 합니다.";

    @Override
    public void run() {
        final String commandInput = InputView.readStartGameOption();
        final Command command = Command.from(commandInput);

        execute(command);
    }

    private void execute(final Command command) {
        if (Command.END.equals(command)) {
            new End().run();
            return;
        }

        if (Command.MOVE.equals(command)) {
            throw new IllegalArgumentException(NOT_STARTED);
        }

        start();
    }

    private void start() {
        final Board board = Board.create(new InitialChessAlignment());
        OutputView.printBoard(board.getPieces());

        new White(board).run();
    }
}
