package chess.controller;

import chess.controller.dto.ChessBoardPositions;
import chess.controller.dto.ChessCommandDto;
import chess.domain.PiecesPosition;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameCommand;
import chess.domain.game.state.ReadyChessGame;
import chess.view.input.InputView;
import chess.view.output.OutputView;
import java.util.function.Supplier;

public final class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartPrefix();

        ChessGame chessGame = retryOnInvalidUserInput(this::startChessGame);
        printChessGameBoard(chessGame);
        play(chessGame);
    }

    private ChessGame startChessGame() {
        ChessCommandDto chessCommands = inputView.readCommands();
        PiecesPosition piecesPosition = new PiecesPosition();
        ChessGame chessGame = new ReadyChessGame(piecesPosition);

        ChessGameCommand gameCommand = chessCommands.toChessGameCommand();
        return chessGame.playByCommand(gameCommand);
    }

    private void play(ChessGame chessGame) {
        while (chessGame.isGameRunnable()) {
            ChessGame recentGame = chessGame;
            chessGame = retryOnInvalidUserInput(() -> playTurn(recentGame));
            printChessGameBoard(chessGame);
        }
    }

    private ChessGame playTurn(ChessGame chessGame) {
        ChessCommandDto chessCommandDto = inputView.readCommands();
        ChessGameCommand chessGameCommand = chessCommandDto.toChessGameCommand();
        return chessGame.playByCommand(chessGameCommand);
    }

    private void printChessGameBoard(ChessGame chessGame) {
        PiecesPosition piecesPosition = chessGame.getPiecesPosition();
        ChessBoardPositions chessBoardPositions = ChessBoardPositions.getInstance();
        outputView.printChessState(chessBoardPositions.getPositions(), piecesPosition.getPiecesPosition());
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }
}
