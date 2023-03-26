package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.domain.dao.ChessGameDao;
import chess.domain.piecesfactory.StartingPiecesFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessGameDao chessGameDao;

    public ChessController(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        OutputView.printGameStartGuideMessage();
        ChessGame chessGame = loadChessGame();
        while (!chessGame.isGameOver()) {
            chessGame = play(chessGame);
        }
    }

    private ChessGame loadChessGame() {
        ChessGame chessGame = chessGameDao.select();
        if (chessGame.isGameOver()) {
            chessGame = ChessGame.from(new StartingPiecesFactory().generate());
            chessGameDao.save(chessGame);
        }
        return chessGame;
    }

    private ChessGame play(ChessGame chessGame) {
        try {
            final Command command = readCommand();
            chessGame = command.execute(chessGame);
            chessGameDao.update(chessGame);
            return chessGame;
        } catch (IllegalArgumentException | IllegalStateException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            return chessGame;
        }
    }

    private Command readCommand() {
        while (true) {
            try {
                return new Command(InputView.readGameCommand());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
