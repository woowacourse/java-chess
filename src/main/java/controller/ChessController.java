package controller;

import static controller.Command.END;
import static controller.Command.MOVE;

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
    private static final String NOT_STARTED = "게임을 먼저 시작해야 합니다.";

    public void run(Board board) {
        if (isStart()) {

            OutputView.printBoard(board.getPieces());

            play(board);
        }
    }

    private boolean isStart() {
        final Command command = Command.from(InputView.readStartGameOption());

        if (END.equals(command)) {
            return false;
        }
        if (MOVE.equals(command)) {
            throw new IllegalArgumentException(NOT_STARTED);
        }
        return true;
    }

    private void play(final Board board) {
        final List<String> gameOption = InputView.readPlayGameOption();
        final Command command = Command.from(gameOption.get(COMMAND));

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
