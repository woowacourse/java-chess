package controller;

import view.GameCommand;
import domain.chessGame.ChessBoard;
import domain.chessGame.ChessBoardGenerator;
import domain.position.ColumnToNumber;
import domain.position.Position;
import domain.position.RowToNumber;
import view.InputView;
import view.OutputView;

import java.util.List;

public final class ChessController {

    public void run() {
        OutputView.printStartMessage();
        GameCommand gameCommand = InputView.readInitialCommand();
        if (gameCommand == GameCommand.START) {
            startGame();
        }
    }

    private void startGame() {
        ChessBoard chessBoard = setUpChessBoard();
        List<String> userInput = InputView.readPlayingCommand();
        GameCommand gameCommand = GameCommand.of(userInput.get(0));

        while (gameCommand != GameCommand.END) {
            executeMoveCommand(chessBoard, userInput);
            userInput = InputView.readPlayingCommand();
            gameCommand = GameCommand.of(userInput.get(0));
        }
    }

    private ChessBoard setUpChessBoard() {
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
        return chessBoard;
    }

    private void executeMoveCommand(ChessBoard chessBoard, List<String> userInput) {
        Position start = convertInputToPosition(userInput.get(1));
        Position end = convertInputToPosition(userInput.get(2));

        chessBoard.movePiece(start, end);
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
    }

    private Position convertInputToPosition(String input) {
        int row = RowToNumber.of(input.charAt(1));
        int column = ColumnToNumber.of(input.charAt(0));

        return Position.of(row, column);
    }
}
