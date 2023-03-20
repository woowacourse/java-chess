package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.view.*;

public class ChessController {

    private final ErrorController errorController;

    public ChessController(ErrorController errorController) {
        this.errorController = errorController;
    }

    public void run() {
        OutputView.printInitialMessage();
        if (Command.START == readStartSign()) {
            playChess();
        }
    }

    private Command readStartSign() {
        Command startSign;
        do {
            startSign = readCommand().getCommand();
        } while (Command.MOVE == startSign);
        return startSign;
    }

    private CommandDto readCommand() {
        return errorController.RetryIfThrowsException(() ->
                InputRenderer.toCommandDto(InputView.readCommand()));
    }

    private void playChess() {
        Board board = Board.create();
        OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));

        CommandDto commandDto = readMove();
        while (commandDto.getCommand() == Command.MOVE) {
            Position sourcePosition = commandDto.getSourcePosition();
            Position targetPosition = commandDto.getTargetPosition();
            errorController.tryCatchStrategy(() -> {
                board.movePiece(sourcePosition, targetPosition);
                OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));
            });
            commandDto = readCommand();
        }
    }

    private CommandDto readMove() {
        CommandDto moveSign;
        do {
            moveSign = readCommand();
        } while (Command.START == moveSign.getCommand());
        return moveSign;
    }

}
