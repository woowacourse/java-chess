package controller;

import domain.GameCommand;
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
        GameCommand gameCommand = receiveStartOrEndCommand(receiveGameCommand());
        if (gameCommand == GameCommand.START) {
            startGame();
        }
    }

    private GameCommand receiveStartOrEndCommand(GameCommand gameCommand) {
        while (gameCommand == GameCommand.MOVE) {
            OutputView.printNotStartedGameMessage();
            gameCommand = receiveGameCommand();
        }
        return gameCommand;
    }

    private GameCommand receiveGameCommand() {
        List<String> userInput = InputView.readUserInput();
        return GameCommand.of(userInput.get(0));
    }

    private void startGame() {
        ChessBoard chessBoard = setUpChessBoard();
        List<String> userInput = InputView.readUserInput();
        GameCommand gameCommand = GameCommand.of(userInput.get(0));

        while (gameCommand != GameCommand.END) {
            executePlayingCommand(chessBoard, userInput);
            userInput = InputView.readUserInput();
            gameCommand = GameCommand.of(userInput.get(0));
        }
    }

    private void executePlayingCommand(ChessBoard chessBoard, List<String> userInput) {
        GameCommand gameCommand = GameCommand.of(userInput.get(0));

        if (gameCommand == GameCommand.START) {
            throw new IllegalArgumentException("[ERROR] 게임 진행 중에는 move와 end 명령어만 입력 가능합니다.");
        }

        if (gameCommand == GameCommand.MOVE) {
            executeMoveCommand(chessBoard, userInput);
        }
    }

    private void executeMoveCommand(ChessBoard chessBoard, List<String> userInput) {
        Position start = inputToPosition(userInput.get(1));
        Position end = inputToPosition(userInput.get(2));

        chessBoard.movePiece(start, end);
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
    }

    private Position inputToPosition(String input) {
        int row = RowToNumber.of(input.charAt(1));
        int column = ColumnToNumber.of(input.charAt(0));

        return Position.of(row, column);
    }

    private ChessBoard setUpChessBoard() {
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
        return chessBoard;
    }
}
