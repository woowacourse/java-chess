package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.GameCommand;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChessGameController {

    private static final Integer GAME_COMMAND_INDEX = 0;

    public void run() {
        OutputView.printStartMessage();
        gameStart();
    }

    private void gameStart() {
        List<String> input = repeatUntilSuccess(InputView::requestGameCommand);
        String inputCommand = input.get(GAME_COMMAND_INDEX);
        try {
            executeGameUntilEnd(GameCommand.findGameCommand(inputCommand), input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            gameStart();
        }
    }

    private void executeGameUntilEnd(GameCommand gameCommand, List<String> input) {
        ChessBoard chessBoard = new ChessBoard();
        while (GameCommand.isNotFinishedGame(gameCommand)) {
            executeStartCommand(gameCommand, chessBoard);
            executeMoveCommand(gameCommand, chessBoard, input);
            List<String> updateInput = repeatUntilSuccess(InputView::requestGameCommand);
            gameCommand = GameCommand.findGameCommand(updateInput.get(GAME_COMMAND_INDEX));
        }
    }

    private void executeStartCommand(GameCommand gameCommand, ChessBoard chessBoard) {
        if (GameCommand.isGameStarted(gameCommand)) {
            printChessBoard(chessBoard);
        }
    }

    private void executeMoveCommand(GameCommand gameCommand, ChessBoard chessBoard, List<String> input) {
        if (GameCommand.isMovedChessPiece(gameCommand)) {
            ChessGame chessGame = new ChessGame(chessBoard);
            List<Square> moveSquare = chessGame.settingMoveSquare(input);
            chessGame.executeTurn(moveSquare.get(0), moveSquare.get(1));
            printChessBoard(chessBoard);
        }
    }

    private void printChessBoard(ChessBoard chessBoard) {
        ChessBoardDto chessBoardDto = chessBoard.createDto();
        OutputView.printChessBoard(chessBoardDto);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            return repeatUntilSuccess(supplier);
        }
    }
}
