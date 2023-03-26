package chess.controller;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.chessgame.ChessGame;
import chess.domain.dao.ChessGameDao;
import chess.domain.piecesfactory.StartingPiecesFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;

public class ChessController {

    private final ChessGameDao chessGameDao;

    public ChessController(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        OutputView.printGameStartGuideMessage();
        ChessGame chessGame = chessGameDao.select();
        if (chessGame.isGameOver()) {
            chessGame = ChessGame.from(new StartingPiecesFactory().generate());
            chessGameDao.save(chessGame);
        }
        while (!chessGame.isGameOver()) {
            chessGame = play(chessGame);
        }
    }

    private ChessGame play(ChessGame chessGame) {
        try {
            final Command command = readCommand();
            final GameActionByState gameActionByState = GameActionByState.valueOfCommand(command);
            chessGame = gameActionByState.execute(command, chessGame);
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

    private ChessGame start(final Command command, ChessGame chessGame) {
        chessGame = chessGame.start();
        OutputView.printBoard(chessGame.getPieces());
        return chessGame;
    }

    private ChessGame move(final Command command, ChessGame chessGame) {
        command.validateCommandSize();
        final Position currentPosition = command.getCurrentPosition();
        final Position targetPosition = command.getTargetPosition();

        chessGame = chessGame.move(currentPosition, targetPosition);

        OutputView.printBoard(chessGame.getPieces());
        return chessGame;
    }

    private ChessGame status(final Command command, final ChessGame chessGame) {
        final Map<Color, Double> scoreByColor = chessGame.calculateScoreByColor();
        OutputView.printScores(scoreByColor);
        OutputView.printWinner(chessGame.findScoreWinner());
        return chessGame;
    }

    private ChessGame end(final Command command, final ChessGame chessGame) {
        return chessGame.end();
    }
}
