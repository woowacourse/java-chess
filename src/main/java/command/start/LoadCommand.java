package command.start;

import database.dao.ChessGameDao;
import domain.ChessGame;
import gameinitializer.DatabaseChessAlignment;
import java.util.List;

public final class LoadCommand implements StartAction {

    private static final int BOARD_NAME_INDEX = 0;
    private static final int LOAD_COMMAND_PARAMETER_SIZE = 1;

    private final List<String> parameter;

    public LoadCommand(final List<String> parameter) {
        this.parameter = parameter;
    }

    @Override
    public ChessGame init() {
        validateParameter(parameter);
        String boardNumber = parameter.get(BOARD_NAME_INDEX);
        return ChessGame.initGame(new DatabaseChessAlignment(new ChessGameDao(), boardNumber));
    }

    private void validateParameter(final List<String> parameter) {
        if (parameter.size() == LOAD_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("로딩 시 파라미터는 1개여야 합니다.");
    }
}
