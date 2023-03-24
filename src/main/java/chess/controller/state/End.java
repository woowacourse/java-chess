package chess.controller.state;

import chess.controller.dao.JdbcDAO;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public final class End implements State {

    public End() {
    }

    public End(ChessGame chessGame) {
        JdbcDAO jdbcDAO = new JdbcDAO();
        jdbcDAO.delete();
        jdbcDAO.insert(chessGame);
    }

    @Override
    public State execute(InputView inputView, OutputView outputView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
