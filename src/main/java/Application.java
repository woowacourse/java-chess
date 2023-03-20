import controller.Command;
import domain.board.Board;
import domain.board.InitialChessAlignment;
import domain.position.Position;
import domain.position.Positions;
import view.InputView;
import view.OutputView;

import java.util.List;

public final class Application {
    private static final int COMMAND_OFFSET = 0;
    private static final int SOURCE_OFFSET = 1;
    private static final int DESTINATION_OFFSET = 2;
    private static final String NOT_STARTED = "게임을 먼저 시작해야 합니다.";

    public static void main(String[] args) {
        if (isStart()) {
            final Board board = Board.create(new InitialChessAlignment());
            OutputView.printBoard(board.getPieces());

            play(board);
        }
    }

    private static boolean isStart() {
        final String commandInput = InputView.readStartGameOption();
        final Command command = Command.from(commandInput);

        if (Command.END.equals(command)) {
            return false;
        }
        if (Command.MOVE.equals(command)) {
            throw new IllegalArgumentException(NOT_STARTED);
        }
        return true;
    }

    private static void play(final Board board) {
        final List<String> gameOption = InputView.readPlayGameOption();
        final Command command = Command.from(gameOption.get(COMMAND_OFFSET));

        if (Command.END.equals(command)) {
            return;
        }

        board.move(getSourcePiece(gameOption), getDestination(gameOption));
        OutputView.printBoard(board.getPieces());

        play(board);
    }

    private static Position getDestination(final List<String> gameOption) {
        return Positions.from(gameOption.get(DESTINATION_OFFSET));
    }

    private static Position getSourcePiece(final List<String> gameOption) {
        return Positions.from(gameOption.get(SOURCE_OFFSET));
    }
}
