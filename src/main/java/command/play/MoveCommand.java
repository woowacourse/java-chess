package command.play;

import domain.ChessGame;
import domain.position.Positions;
import java.util.List;
import view.OutputView;

public final class MoveCommand implements PlayAction {
    private static final int MOVE_COMMAND_PARAMETER_SIZE = 2;
    private static final int POSITION_SOURCE = 0;
    private static final int POSITION_DESTINATION = 1;
    private final List<String> parameter;

    public MoveCommand(final List<String> parameter) {
        validateParameter(parameter);
        this.parameter = parameter;
    }

    @Override
    public boolean execute(ChessGame chessGame) {
        boolean isEndedGame = chessGame.movePiece(
                Positions.from(parameter.get(POSITION_SOURCE)),
                Positions.from(parameter.get(POSITION_DESTINATION)));
        OutputView.printBoard(chessGame.getPieces());
        return !isEndedGame;
    }

    private void validateParameter(final List<String> parameter) {
        if (parameter.size() == MOVE_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("이동 커맨드의 파라미터는 2개입니다.");
    }
}
