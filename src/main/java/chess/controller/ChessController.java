package chess.controller;

import chess.controller.dto.PlayRequest;
import chess.controller.state.GameState;
import chess.controller.state.Ready;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import chess.model.position.PositionConverter;
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
            return playGame(gameState);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return gameState;
        }
    }

    private GameState playGame(final GameState gameState) {
        final PlayRequest playRequest = inputView.readPlayCommand();
        final GameCommand gameCommand = GameCommand.findGameCommand(playRequest.getCommand());
        final Position source = PositionConverter.convert(playRequest.getSource());
        final Position target = PositionConverter.convert(playRequest.getTarget());

        return gameState.execute(gameCommand, source, target);
    }

    private void printChessBoard(final GameState gameState, final ChessGame chessGame) {
        if (gameState.isPlay()) {
            outputView.printChessBoard(chessGame.getChessBoard());
        }
    }
}
