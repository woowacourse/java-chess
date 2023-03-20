package chess.controller;

import chess.domain.commnad.Command;
import chess.domain.game.ChessGame;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.ResultView;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ResultView resultView;

    public ChessGameController(final InputView inputView, final OutputView outputView, final ResultView resultView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.resultView = resultView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());

        outputView.printStartMessage();

        Command command = inputView.readGameCommand();

        playChess(chessGame, command);

        resultView.printGameEnd();
    }

    private void playChess(ChessGame chessGame, Command command) {
        while (!isGameEnd(chessGame, command)) {

            chessGame = createNewChessGame(chessGame, command);
            tryChessMove(chessGame, command);
            outputView.printBoard(chessGame.getBoard());

            if (isGameDone(chessGame)) {
                break;
            }

            command = inputView.readGameCommand();
        }
    }

    private boolean isGameEnd(final ChessGame chessGame, final Command command) {
        if (isCommandStatus(chessGame, command) || command.isGameStop()) {
            return true;
        }

        return false;
    }

    private boolean isCommandStatus(final ChessGame chessGame, final Command command) {
        if (command.isStatus()) {
            resultView.printScore(chessGame.calculateScoreOfUpperTeam(), chessGame.calculateScoreOfLowerTeam());
            resultView.printWinner(chessGame.calculateScoreOfUpperTeam(),
                    chessGame.calculateScoreOfLowerTeam());
            return true;
        }
        return false;
    }

    private boolean isGameDone(final ChessGame chessGame) {
        if (chessGame.isGameDone() && chessGame.isUpperTeamWin()) {
            resultView.printWinnerIsUpperTeam();
            return true;
        }

        if (chessGame.isGameDone()) {
            resultView.printWinnerIsLowerTeam();
            return true;
        }

        return false;
    }

    private void tryChessMove(final ChessGame chessGame, final Command command) {
        if (!command.isMove()) {
            return;
        }

        try {
            chessGame.move(command.findSelectedPiece(), command.findDestination());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private ChessGame createNewChessGame(ChessGame chessGame, final Command command) {
        if (command.isCreateNewGame()) {
            chessGame.initBoard();
        }
        return chessGame;
    }
}
