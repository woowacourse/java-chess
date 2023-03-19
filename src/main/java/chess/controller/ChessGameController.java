package chess.controller;

import chess.domain.commnad.Command;
import chess.domain.game.ChessGame;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());

        outputView.printStartMessage();

        Command command = inputView.readGameCommand();

        playChess(chessGame, command);
    }

    private void playChess(ChessGame chessGame, Command command) {
        while (!command.isGameStop()) {
            try {
                chessGame = createNewChessGame(chessGame, command);
                tryChessMove(chessGame, command);
                outputView.printBoard(chessGame.getBoard());
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
            chessGame = new ChessGame(BoardFactory.createBoard());
        }
        return chessGame;
    }
}
