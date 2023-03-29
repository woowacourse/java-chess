package controller.command.play;

import database.dao.ChessGameDao;
import domain.ChessGame;
import java.util.Map;

public final class SaveCommand implements PlayAction {
    private static final int SAVE_COMMAND_PARAMETER_SIZE = 1;
    private static final int BOARD_NAME_PARAMETER = 1;

    @Override
    public boolean execute(final ChessGame chessGame, final Map<Integer, String> parameters) {
        validateParameters(parameters);
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.saveBoard(chessGame, parameters.get(BOARD_NAME_PARAMETER));
        return true;
    }

    private void validateParameters(final Map<Integer, String> parameters) {
        if (parameters.size() == SAVE_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("저장 커맨드의 파라미터는 방 이름 1개입니다.");
    }
}
