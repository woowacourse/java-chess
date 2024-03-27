package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class ChessGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        ChessBoard chessBoard = generateChessBoard();
        GameState gameState = new Ready(chessBoard);
        outputView.printStartMessage();

        playGame(gameState, chessBoard);
    }

    private ChessBoard generateChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialBoard();
        return chessBoard;
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
        gameState = gameState.play(command);
        return gameState;
    }


    private void printChessBoardInProgress(GameState gameState, ChessBoard chessBoard) {
        ChessBoardDto chessBoardDto;
        if (!gameState.isEnd()) {
            chessBoardDto = chessBoard.convertToDto();
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
