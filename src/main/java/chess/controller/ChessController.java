package chess.controller;

import chess.controller.command.chess.ChessGameCommand;
import chess.controller.command.execute.ExecuteCommand;
import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.service.PieceService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final ChessGameService chessGameService;
    private final PieceService pieceService;

    public ChessController(final ChessGameService chessGameService, final PieceService pieceService) {
        this.chessGameService = chessGameService;
        this.pieceService = pieceService;
    }

    public void run() {
        outputView.printStartMessage(chessGameService.findAllChessGameId());
        final ChessGame chessGame = readChessGame();
        while (chessGame.isRunning()) {
            playChessGame(chessGame);
            checkKing(chessGame);
        }
    }

    private ChessGame readChessGame() {
        while (true) {
            try {
                final ChessGameCommand chessGameCommand = inputView.readChessGameCommand();
                return chessGameCommand.execute(chessGameService, pieceService, outputView);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void playChessGame(final ChessGame chessGame) {
        try {
            final ExecuteCommand executeCommand = inputView.readExecuteCommand();
            executeCommand.execute(chessGame, chessGameService, pieceService, outputView);
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void checkKing(final ChessGame chessGame) {
        if (chessGame.isKingDied()) {
            chessGame.end();
            pieceService.deleteAll(chessGame.getId());
            chessGameService.delete(chessGame.getId());
            outputView.printDoneMessage();
        }
    }
}
