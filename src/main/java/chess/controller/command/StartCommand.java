package chess.controller.command;

import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public final class StartCommand implements Command {

    private final OutputView outputView = new OutputView();
    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.startGame();
        Long gameId = chessGameDao.createGame();
        chessGame.setGameId(gameId);
        chessGameDao.saveGame(chessGame);
        outputView.printBoard(chessGame.getBoard());
        outputView.printGameId(gameId);
    }
}
