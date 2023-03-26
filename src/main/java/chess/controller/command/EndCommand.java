package chess.controller.command;

import chess.dao.ChessDao;
import chess.domain.ChessGame;
import chess.view.OutputView;

public class EndCommand implements Command {

    public EndCommand() {
    }

    @Override
    public void execute(ChessGame chessGame, final OutputView outputView) {
        chessGame.end();
        ChessDao chessDao = new ChessDao();
        chessDao.save(chessGame.getChessBoard());
    }
}
