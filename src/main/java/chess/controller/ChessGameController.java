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

        resultView.printScore(chessGame.calculateScoreOfUpperTeam(), chessGame.calculateScoreOfLowerTeam());
        resultView.printWinner(chessGame.calculateScoreOfUpperTeam(), chessGame.calculateScoreOfLowerTeam());
    }

    private void playChess(ChessGame chessGame, Command command) {
        while (!command.isGameStop() && !chessGame.isGameDone()) {
            try {
                chessGame = createNewChessGame(chessGame, command);
                tryChessMove(chessGame, command);
                outputView.printBoard(chessGame.getBoard());
                if (chessGame.isGameDone()) {
                    break;
                }
                command = inputView.readGameCommand();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                command = inputView.readGameCommand();
            }
        }
    }

    private void tryChessMove(final ChessGame chessGame, final Command command) {
        if (command.isMove()) {
            chessGame.move(command.findSelectedPiece(), command.findDestination());
        }
    }

    private ChessGame createNewChessGame(ChessGame chessGame, final Command command) {
        if (command.isCreateNewGame()) {
            chessGame.initBoard();
        }
        return chessGame;
    }
}
