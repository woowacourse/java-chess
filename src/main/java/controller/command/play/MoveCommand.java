package controller.command.play;

import domain.ChessGame;
import domain.position.Positions;
import java.util.Map;
import view.OutputView;

public final class MoveCommand implements PlayAction {
    private static final int MOVE_COMMAND_PARAMETER_SIZE = 2;
    private static final int SOURCE_PARAMETER = 1;
    private static final int DESTINATION_PARAMETER = 2;

    @Override
    public boolean execute(final ChessGame chessGame, final Map<Integer, String> parameters) {
        validateParameters(parameters);
        boolean isEndedGame = chessGame.movePiece(
                Positions.from(parameters.get(SOURCE_PARAMETER)),
                Positions.from(parameters.get(DESTINATION_PARAMETER)));
        OutputView.printBoard(chessGame.getPieces());
        return !isEndedGame;
    }

    private void validateParameters(final Map<Integer, String> parameters) {
        if (parameters.size() == MOVE_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("이동 커맨드의 파라미터는 2개입니다.");
    }
}
