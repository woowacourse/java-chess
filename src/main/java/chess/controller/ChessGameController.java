package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChessGameController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        GameState gameState = new Ready(chessBoard);
        outputView.printStartMessage();

        playGame(gameState, chessBoard);
    }

    private void playGame(GameState gameState, ChessBoard chessBoard) {
        while (!gameState.isEnd()) {
            GameState currentGameState = gameState;
            gameState = repeatUntilSuccess(() -> playEachTurn(currentGameState));

            printChessBoardInProgress(gameState, chessBoard);
        }
    }

    private GameState playEachTurn(GameState gameState) {
        List<String> command = inputView.readCommand();
        return gameState.play(command);
    }

    private void printChessBoardInProgress(GameState gameState, ChessBoard chessBoard) {
        if (!gameState.isEnd()) {
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard);
            outputView.printChessBoard(chessBoardDto);
        }
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            outputView.printErrorMessage(e.getMessage());
            return repeatUntilSuccess(supplier);
        }
    }
}
