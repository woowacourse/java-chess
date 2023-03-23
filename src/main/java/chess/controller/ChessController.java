package chess.controller;

import chess.controller.dto.PlayRequest;
import chess.model.dto.PlayDto;
import chess.model.game.ChessGame;
import chess.model.game.GameCommand;
import chess.model.game.state.GameState;
import chess.model.game.state.Ready;
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
            final PlayRequest playRequest = inputView.readPlayCommand();
            final PlayDto playDto = convertDtoFromRequest(playRequest);

            return gameState.execute(playDto);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return gameState;
        }
    }

    private PlayDto convertDtoFromRequest(final PlayRequest playRequest) {
        final GameCommand gameCommand = GameCommand.findGameCommand(playRequest.getCommand());
        final Position sourcePosition = PositionConverter.convert(playRequest.getSource());
        final Position targetPosition = PositionConverter.convert(playRequest.getTarget());

        return new PlayDto(gameCommand, sourcePosition, targetPosition);
    }

    private void printChessBoard(final GameState gameState, final ChessGame chessGame) {
        if (gameState.isPlay()) {
            outputView.printChessBoard(chessGame.getChessBoard());
        }
    }
}
