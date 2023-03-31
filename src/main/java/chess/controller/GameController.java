package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.dao.ChessGameDao;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {

    private final ChessGameDao chessGameDao;

    public GameController(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        ChessGame chessGame = chessGameDao.select();
        if (chessGame == null) {
            chessGame = new ChessGame(ChessBoard.GenerateChessBoard());
            chessGameDao.save(chessGame);
        }
        startGame(chessGame);
        playGame(chessGame, chessGameDao);
    }

    private ChessGame startGame(ChessGame chessGame) {
        try {
            OutputView.gameStartMessage();
            Command command = CommandType.makeCommand(InputView.readCommand());
            command.execute(chessGame);
            return chessGame;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return startGame(chessGame);
    }

    private void playGame(ChessGame chessGame, ChessGameDao chessGameDao) {
        while (chessGame.isPlaying()) {
            try {
                Command command = CommandType.makeCommand(InputView.readCommand());
                command.execute(chessGame);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            chessGameDao.update(chessGame);
        }
    }
}
