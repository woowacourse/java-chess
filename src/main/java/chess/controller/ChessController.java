package chess.controller;

import chess.controller.dto.CommandDto;
import chess.controller.dto.InputRenderer;
import chess.controller.dto.OutputRenderer;
import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

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
        Board board = BoardGenerator.createBoard();
        OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));

        CommandDto commandDto = readMove();
        while (commandDto.getCommand() == Command.MOVE) {
            List<Integer> source = commandDto.getSource();
            List<Integer> target = commandDto.getTarget();
            Position sourcePosition = new Position(source.get(0), source.get(1));
            Position targetPosition = new Position(target.get(0), target.get(1));
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
