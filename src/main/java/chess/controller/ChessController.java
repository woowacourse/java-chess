package chess.controller;

import chess.controller.dto.ChessBoardDto;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Supplier;

import static chess.controller.ChessGameCommand.START;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printStartMessage();
        if (readChessStartCommand() == START) {
            outputView.printChessBoard(ChessBoardDto.from(chessGame.getBoard()));
            playChessGame();
        }
    }

    private ChessGameCommand readChessStartCommand() {
        return repeatUntilGetValidInput(
                () -> ChessGameCommand.generateExecuteCommand(inputView.readChessExecuteCommand())
        );
    }

    private void playChessGame() {
        try {
            repeatMove();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            playChessGame();
        }
    }

    private void repeatMove() {
        String gameCommandInput = inputView.readChessGameCommand();
        while (!ChessGameCommand.isEnd(gameCommandInput)) {
            MoveCommand chessMoveCommand = MoveCommand.from(gameCommandInput);
            chessGame.move(chessMoveCommand.getSource(), chessMoveCommand.getDestination());
            outputView.printChessBoard(ChessBoardDto.from(chessGame.getBoard()));
            gameCommandInput = getReadChessGameCommand();
        }
    }

    private String getReadChessGameCommand() {
        System.out.println(chessGame.isFinished());
        if (chessGame.isFinished()) {
            outputView.printResult(chessGame.getResult());
            return "end";
        }
        return inputView.readChessGameCommand();
    }

    private <T> T repeatUntilGetValidInput(final Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return repeatUntilGetValidInput(inputReader);
        }
    }
}
