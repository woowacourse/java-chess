package controller.command.start;

import database.dao.ChessGameDao;
import domain.ChessGame;
import gameinitializer.DatabaseChessAlignment;
import java.util.Map;

public final class LoadCommand implements StartAction {

    private static final int BOARD_NAME_PARAMETER = 1;
    private static final int LOAD_COMMAND_PARAMETER_SIZE = 1;


    @Override
    public ChessGame init(final Map<Integer, String> parameters) {
        validateParameters(parameters);
        String boardNumber = parameters.get(BOARD_NAME_PARAMETER);
        return ChessGame.initGame(new DatabaseChessAlignment(new ChessGameDao(), boardNumber));
    }

    private void validateParameters(final Map<Integer, String> parameters) {
        if (parameters.size() == LOAD_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("로딩 시 파라미터는 1개여야 합니다.");
    }
}
