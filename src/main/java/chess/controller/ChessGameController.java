package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
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
    private static final Integer FORMATTING_TO_UPPERCASE_LETTER_ASCII_NUMBER = 64;
    private static final Integer FORMATTING_TO_LOWERCASE_LETTER_ASCII_NUMBER = 96;

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
        while (gameCommand != GameCommand.END) {
            executeStartCommand(gameCommand, chessBoard);
            executeMoveCommand(gameCommand, chessBoard, input);
            List<String> updateInput = repeatUntilSuccess(InputView::requestGameCommand);
            gameCommand = GameCommand.findGameCommand(updateInput.get(GAME_COMMAND_INDEX));
        }
    }

    private void executeStartCommand(GameCommand gameCommand, ChessBoard chessBoard) {
        if (gameCommand.equals(GameCommand.START)) {
            printChessBoard(chessBoard);
        }
    }

    private void executeMoveCommand(GameCommand gameCommand, ChessBoard chessBoard, List<String> input) {
        if (gameCommand.equals(GameCommand.MOVE)) {
            ChessGame chessGame = new ChessGame(chessBoard);
            List<Square> moveSquare = createMoveSquare(extractMoveSquare(input));
            chessGame.executeTurn(moveSquare.get(0), moveSquare.get(1));
            printChessBoard(chessBoard);
        }
    }

    private List<String> extractMoveSquare(List<String> input) {
        return input.subList(1, input.size());
    }

    private List<Square> createMoveSquare(List<String> moveSquare) {
        Square moveSource = createSquare(moveSquare.get(0));
        Square target = createSquare(moveSquare.get(1));
        return List.of(moveSource, target);
    }

    private Square createSquare(String inputSquare) {
        char letter = inputSquare.charAt(0);
        char number = inputSquare.charAt(1);
        Numbering numbering = Numbering.findNumbering(number - 48);
        if (Character.isUpperCase(letter)) {
            return new Square(
                    Lettering.findLettering(letter - FORMATTING_TO_UPPERCASE_LETTER_ASCII_NUMBER),
                    numbering);
        }
        return new Square(
                Lettering.findLettering(letter - FORMATTING_TO_LOWERCASE_LETTER_ASCII_NUMBER),
                numbering);
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
