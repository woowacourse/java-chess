package controller;

import static controller.ProgressCommand.END;

import domain.board.Board;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class ChessController {

    private static final int COMMAND = 0;
    private static final int SOURCE_PIECE = 1;
    private static final int DESTINATION = 2;

    public void run(Board board) {
        if (isGameStart()) {
            OutputView.printBoard(board.getPieces());
            play(board);
        }
    }

    private boolean isGameStart() {
        try {
            final StartCommand command = StartCommand.from(InputView.readStartGameOption());
            return StartCommand.START.equals(command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return isGameStart();
        }
    }

    private void play(final Board board) {
        final List<String> gameOption = InputView.readPlayGameOption();
        final ProgressCommand command = ProgressCommand.from(gameOption.get(COMMAND));

        if (END.equals(command)) {
            return;
        }

        board.move(getSourcePiece(gameOption), getDestination(gameOption));
        OutputView.printBoard(board.getPieces());

        play(board);
    }

    private Position getDestination(final List<String> gameOption) {
        return Positions.from(gameOption.get(DESTINATION));
    }

    private Position getSourcePiece(final List<String> gameOption) {
        return Positions.from(gameOption.get(SOURCE_PIECE));
    }
}
