package chess.controller.command;

import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public final class StartCommand implements Command {

    private final OutputView outputView = new OutputView();
    private final ChessGameDao chessGameDaoImpl = ChessGameDaoImpl.getInstance();

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.startGame();
        Long gameId = chessGameDaoImpl.createGame();
        chessGame.setGameId(gameId);
        chessGameDaoImpl.saveGame(chessGame);
        outputView.printBoard(chessGame.getBoard());
        outputView.printGameId(gameId);
    }
}
