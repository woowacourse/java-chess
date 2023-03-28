package command.play;

import database.dao.ChessGameDao;
import domain.ChessGame;
import java.util.List;

public final class SaveCommand implements PlayAction {
    private static final int SAVE_COMMAND_PARAMETER_SIZE = 1;
    private static final int BOARD_NAME = 0;
    private final List<String> parameter;

    public SaveCommand(final List<String> parameter) {
        validateParameter(parameter);
        this.parameter = parameter;
    }

    @Override
    public boolean execute(ChessGame chessGame) {
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.saveBoard(chessGame, parameter.get(BOARD_NAME));
        return true;
    }

    private void validateParameter(final List<String> parameter) {
        if (parameter.size() == SAVE_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("저장 커맨드의 파라미터는 방 이름 1개입니다.");
    }
}
