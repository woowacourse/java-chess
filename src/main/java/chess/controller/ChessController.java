package chess.controller;

import chess.controller.dto.ChessBoardDto;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

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
        while (true) {
            try {
                return ChessGameCommand.from(inputView.readChessExecuteCommand());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void playChessGame() {
        while (true) {
            try {
                repeatMove();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void repeatMove() {
        String gameCommandInput = inputView.readChessGameCommand();
        while (ChessGameCommand.from(gameCommandInput) != ChessGameCommand.END) {
            MoveCommand chessMoveCommand = MoveCommand.from(gameCommandInput);
            chessGame.move(chessMoveCommand.getSource(), chessMoveCommand.getDestination());
            outputView.printChessBoard(ChessBoardDto.from(chessGame.getBoard()));
            gameCommandInput = inputView.readChessGameCommand();
        }
    }
}
