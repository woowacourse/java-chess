package controller;

import domain.board.Board;
import domain.board.InitialChessAlignment;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class ChessController {

    private static final int COMMAND_INDEX = 0;

    public void run() {
        final Board board = Board.create(new InitialChessAlignment());
        if (Command.START.equals(Command.from(InputView.readStartGameOption()))) {
            OutputView.printBoard(board.getPieces());
            play(board);
        }
    }

    private void play(final Board board) {
        final List<String> gameOption = InputView.readPlayGameOption();
        final Command command = Command.from(gameOption.get(COMMAND_INDEX));
        if (Command.END.equals(command)) {
            return;
        }

        final Position from = Positions.from(gameOption.get(1));
        final Position to = Positions.from(gameOption.get(2));

        board.move(from, to);
        OutputView.printBoard(board.getPieces());

        play(board);
    }
}
