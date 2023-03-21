package chess.controller;

import chess.model.game.ChessGame;
import chess.model.game.state.GameState;
import chess.model.game.state.Ready;
import chess.view.ChessBoardResponse;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(final ChessGame chessGame) {
        outputView.guideGameStart();
        GameState gameState = new Ready(chessGame);

        while (gameState.isContinue()) {
            gameState = run(gameState);
            printChessBoard(gameState, chessGame);
        }
    }

    private GameState run(final GameState gameState) {
        try {
            final PlayRequest playRequest = inputView.readPlayCommand();

            return gameState.execute(playRequest);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return gameState;
        }
    }

    private void printChessBoard(final GameState gameState, final ChessGame chessGame) {
        if (gameState.isPlay()) {
            final ChessBoardResponse response = chessGame.getChessBoard();

            outputView.printChessBoard(response);
        }
    }
}
